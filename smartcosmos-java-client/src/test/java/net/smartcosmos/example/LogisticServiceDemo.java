/*
 * SMART COSMOS SDK
 * (C) Copyright 2013-2014, Smartrac Technology Fletcher, Inc.
 * 267 Cane Creek Rd, Fletcher, NC, 28732, USA
 * All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.smartcosmos.example;

import net.smartcosmos.client.connectivity.ServerContext;
import net.smartcosmos.client.connectivity.ServiceException;
import net.smartcosmos.client.event.EventFactory;
import net.smartcosmos.client.event.IEventClient;
import net.smartcosmos.client.file.FileFactory;
import net.smartcosmos.client.file.IFileClient;
import net.smartcosmos.client.interaction.IInteractionClient;
import net.smartcosmos.client.interaction.InteractionFactory;
import net.smartcosmos.client.metadata.IMetadataClient;
import net.smartcosmos.client.metadata.MetadataFactory;
import net.smartcosmos.client.object.IObjectClient;
import net.smartcosmos.client.object.ObjectFactory;
import net.smartcosmos.client.object.address.IObjectAddressClient;
import net.smartcosmos.client.object.address.ObjectAddressFactory;
import net.smartcosmos.client.registration.IRegistrationClient;
import net.smartcosmos.client.registration.RegistrationFactory;
import net.smartcosmos.client.registration.RegistrationResponse;
import net.smartcosmos.client.relationship.IRelationshipClient;
import net.smartcosmos.client.relationship.RelationshipFactory;
import net.smartcosmos.client.tag.ITagClient;
import net.smartcosmos.client.tag.TagFactory;
import net.smartcosmos.builder.MetadataBuilder;
import net.smartcosmos.model.base.EntityReferenceType;
import net.smartcosmos.model.context.IMetadata;
import net.smartcosmos.model.context.MetadataDataType;
import net.smartcosmos.model.event.IEvent;
import net.smartcosmos.objects.builder.FileBuilder;
import net.smartcosmos.objects.builder.InteractionBuilder;
import net.smartcosmos.objects.builder.ObjectAddressBuilder;
import net.smartcosmos.objects.builder.ObjectBuilder;
import net.smartcosmos.objects.builder.RelationshipBuilder;
import net.smartcosmos.objects.builder.TagCollectionBuilder;
import net.smartcosmos.objects.model.context.IFile;
import net.smartcosmos.objects.model.context.IObject;
import net.smartcosmos.objects.model.context.IObjectAddress;
import net.smartcosmos.objects.model.context.IObjectInteraction;
import net.smartcosmos.objects.model.context.IRelationship;
import net.smartcosmos.objects.model.context.ITag;
import net.smartcosmos.pojo.base.ResponseEntity;
import net.smartcosmos.pojo.base.Result;
import net.smartcosmos.util.json.ViewType;
import org.json.JSONException;
import org.restlet.data.MediaType;

import java.io.File;
import java.util.Calendar;
import java.util.Collection;
import java.util.UUID;

public class LogisticServiceDemo
{
    private static final String OPERATOR_TYPE = "Operator";

    private static final String VEHICLE_TYPE = "Vehicle";

    private static final String DRIVER_TYPE = "Driver";

    private static final String PACKAGE_TYPE = "Package";

    private static final String DELIVERY_TYPE = "Delivery";

    private static final String HOME_ADDRESS_TYPE = "Home Address";

    private static final String HAZMAT_TAG = "#hazmat";

    private static final String KEY_DATE_OF_BIRTH = "dob";

    private static final String MIME_TYPE = "image/jpg";

    private final String realm;

    private final String emailAddress;

    private final String assignedPassword;

    private final String photoPath;

    private String operatorUrn;

    private String vehicleUrn;

    private String driverRelationshipUrn;

    private String packageUrn;

    private String packageDeliveryUrn;

    private IObject packageObject;

    private IObject operatorObject;

    private IObject vehicleObject;

    private String hazmatTagUrn;

    private String operatorDoBUrn;

    private String fileUrn;

    private long startTimestamp;

    public LogisticServiceDemo(String realm, String emailAddress, String assignedPassword, String photoPath)
    {
        this.realm = realm;
        this.emailAddress = emailAddress;
        this.assignedPassword = assignedPassword;
        this.photoPath = photoPath;
    }

    public static void main(String[] args) throws ServiceException, JSONException
    {
        LogisticServiceDemo instance = new LogisticServiceDemo(args[0], args[1], args[2], args[3]);
        instance.registerAccount();
    }

    private void registerAccount() throws ServiceException, JSONException
    {
        IRegistrationClient client = RegistrationFactory.createClient();

        System.out.printf("Checking to determine if realm '%s' is available for registration\n", realm);
        boolean isAvailable = client.isRealmAvailable(realm);
        System.out.printf("Available? %s\n", isAvailable);

        if (isAvailable)
        {
            RegistrationResponse response = client.register(emailAddress);
            System.out.printf("Assigned URN %s for realm %s\n", response.getUrn(), response.getRealm());
        } else
        {
            System.out.printf("Registration Confirmed\n");
            run();
        }
    }

    private void run() throws ServiceException, JSONException
    {
        ServerContext context = new ServerContext(emailAddress, assignedPassword);

        startTimestamp = System.currentTimeMillis();

        createOperator(context);
        createVehicle(context);
        createDriverRelationship(context);
        createPackage(context);
        createDelivery(context);
        showEventStream(context);

    }

    private void showEventStream(ServerContext context) throws ServiceException
    {
        IEventClient client = EventFactory.createClient(context);

        Collection<IEvent> events = client.findSince(startTimestamp, ViewType.Full);

        for (IEvent event : events)
        {
            System.out.println(event.toString());
        }
    }

    private void createOperator(ServerContext context) throws ServiceException, JSONException
    {
        IObjectClient client = ObjectFactory.createClient(context);

        String objectUrn = "urn:rfid:badge:" + UUID.randomUUID();

        IObject entity = new ObjectBuilder(objectUrn)
                .setName("Joe Example")
                .setDescription("Logistics Specialist")
                .setType(OPERATOR_TYPE)
                .setMoniker("851142")   // System of Record Employee ID
                .build();

        operatorUrn = extractUrn(client.create(entity));
        System.out.printf("Assigned Operator URN %s\n", operatorUrn);

        setHomeAddress(context);
        setHazMatQualifiedTag(context);
        setDateOfBirth(context);
        setPhoto(context);
    }

    private void setPhoto(ServerContext context) throws ServiceException
    {
        IFileClient client = FileFactory.createClient(context);

        IFile fileDefinition = new FileBuilder(MIME_TYPE)
                .setEntityReferenceType(EntityReferenceType.Object)
                .setReferenceUrn(operatorUrn)
                .build();

        fileUrn = extractUrn(client.create(fileDefinition));
        System.out.printf("Assigned File URN %s\n", fileUrn);

        File f = new File(photoPath);
        ResponseEntity responseEntity = client.uploadOctetStream(fileUrn, f, MediaType.IMAGE_JPEG);
        System.out.printf("File Upload Result: %s\n", responseEntity.getCode());

        Collection<IFile> filesPost = client.listOwnedBy(EntityReferenceType.Object, operatorUrn, ViewType.Full);
        IFile operatorPhotoFile = filesPost.iterator().next();
        System.out.printf("Operator Photo Digital Signature:\n%s\n", operatorPhotoFile.getDigitalSignature());
    }

    private void setDateOfBirth(ServerContext context) throws ServiceException, JSONException
    {
        IMetadataClient client = MetadataFactory.createClient(context);

        Calendar calendar = Calendar.getInstance();
        calendar.set(1980, 1, 1, 0, 0, 0);

        String encoded = client.encodeMetadata(MetadataDataType.LongType, calendar.getTimeInMillis());

        IMetadata metadata = new MetadataBuilder(MetadataDataType.LongType)
                .setKey(KEY_DATE_OF_BIRTH)
                .setRawValue(encoded.getBytes())
                .setEntityReferenceType(EntityReferenceType.Object)
                .setReferenceUrn(operatorUrn)
                .build();

        operatorDoBUrn = extractUrn(client.upsert(metadata));
        System.out.printf("Assigned Operator DoB Metadata URN %s\n", operatorDoBUrn);
    }

    private void setHazMatQualifiedTag(ServerContext context) throws ServiceException
    {
        ITagClient client = TagFactory.createClient(context);

        Collection<ITag> tags = new TagCollectionBuilder()
                .addTag(HAZMAT_TAG)
                .build();

        Collection<ResponseEntity> responses = client.assign(EntityReferenceType.Object, operatorUrn, tags);
        for (ResponseEntity entity : responses)
        {
            hazmatTagUrn = extractUrn(entity);
            System.out.printf("Assigned %s Tag URN %s", HAZMAT_TAG, hazmatTagUrn);
        }
    }

    private void setHomeAddress(ServerContext context) throws ServiceException
    {
        IObjectAddressClient client = ObjectAddressFactory.createClient(context);

        IObject myOperator = findOperator(context);

        IObjectAddress address = new ObjectAddressBuilder(myOperator)
                .setLine1("123 Street")
                .setType(HOME_ADDRESS_TYPE)
                .setRecordedTimestamp(System.currentTimeMillis())
                .setCity("Somewhere")
                .setStateProvince("TX")
                .setCountryAbbreviation("US")
                .build();

        String addressUrn = extractUrn(client.create(address));
        System.out.printf("Assigned Address URN %s", addressUrn);
    }

    private String extractUrn(ResponseEntity responseEntity)
    {
        if (responseEntity.getCode() == Result.OK.getCode())
        {
            return responseEntity.getMessage();
        } else
        {
            throw new IllegalStateException("Result was not OK: " + responseEntity.getCode());
        }
    }

    private void createVehicle(ServerContext context) throws ServiceException
    {
        IObjectClient client = ObjectFactory.createClient(context);

        String objectUrn = "urn:rfid:plate:" + UUID.randomUUID();

        IObject entity = new ObjectBuilder(objectUrn)
                .setName("F-250")
                .setDescription("TX 234-A76")
                .setType(VEHICLE_TYPE)
                .setMoniker("327146")   // System of Record Employee ID
                .build();

        vehicleUrn = extractUrn(client.create(entity));
        System.out.printf("Assigned Vehicle URN %s\n", vehicleUrn);
    }

    private void createDriverRelationship(ServerContext context) throws ServiceException
    {
        IRelationshipClient client = RelationshipFactory.createClient(context);

        IRelationship relationship = new RelationshipBuilder(EntityReferenceType.Object, vehicleUrn)
                .setRelatedEntityReferenceType(EntityReferenceType.Object)
                .setRelatedReferenceUrn(operatorUrn)
                .setType(DRIVER_TYPE)
                .build();

        driverRelationshipUrn = extractUrn(client.upsert(relationship));
        System.out.printf("Assigned Driver Relationship URN %s\n", driverRelationshipUrn);
    }

    private void createPackage(ServerContext context) throws ServiceException
    {
        IObjectClient client = ObjectFactory.createClient(context);

        String objectUrn = "urn:rfid:package:" + UUID.randomUUID();

        IObject entity = new ObjectBuilder(objectUrn)
                .setName("Mid-size heavy box")
                .setType(PACKAGE_TYPE)
                .build();

        packageUrn = extractUrn(client.create(entity));
        System.out.printf("Assigned Package URN %s\n", packageUrn);
    }

    private void createDelivery(ServerContext context) throws ServiceException
    {
        IInteractionClient client = InteractionFactory.createClient(context);

        IRelationship driverRelationship = findDriverRelationship(context);
        IObject packageObject = findPackage(context);

        IObjectInteraction interaction = new InteractionBuilder(System.currentTimeMillis())
                .setType(DELIVERY_TYPE)
                .setEntityReferenceType(EntityReferenceType.Relationship)
                .setReferenceUrn(driverRelationship.getUrn())
                .setObject(packageObject)
                .setMoniker("back door")
                .build();

        packageDeliveryUrn = extractUrn(client.create(interaction));
        System.out.printf("Assigned Package Delivery URN %s\n", packageDeliveryUrn);
    }

    private IObject findOperator(ServerContext context) throws ServiceException
    {
        if (operatorObject == null)
        {
            IObjectClient client = ObjectFactory.createClient(context);
            operatorObject = client.findByExactObjectUrn(operatorUrn);
        }

        return operatorObject;
    }

    private IObject findVehicle(ServerContext context) throws ServiceException
    {
        if (vehicleObject == null)
        {
            IObjectClient client = ObjectFactory.createClient(context);
            vehicleObject = client.findByExactObjectUrn(vehicleUrn);
        }

        return vehicleObject;
    }

    private IObject findPackage(ServerContext context) throws ServiceException
    {
        if (packageObject == null)
        {
            IObjectClient client = ObjectFactory.createClient(context);
            packageObject = client.findByExactObjectUrn(packageUrn);
        }

        return packageObject;
    }

    private IRelationship findDriverRelationship(ServerContext context) throws ServiceException
    {
        IRelationshipClient client = RelationshipFactory.createClient(context);
        return client.findSpecificRelationship(EntityReferenceType.Object,
                vehicleUrn,
                EntityReferenceType.Object,
                operatorUrn,
                DRIVER_TYPE);
    }
}

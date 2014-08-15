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

package net.smartcosmos.catalogs;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.smartcosmos.catalogs.builder.BookBuilder;
import net.smartcosmos.catalogs.builder.ChapterBuilder;
import net.smartcosmos.catalogs.builder.ChapterSectionBuilder;
import net.smartcosmos.catalogs.builder.LibraryBuilder;
import net.smartcosmos.catalogs.builder.PageBuilder;
import net.smartcosmos.catalogs.builder.PageEntryBuilder;
import net.smartcosmos.catalogs.builder.ShelfBuilder;
import net.smartcosmos.catalogs.model.context.ILibrary;
import net.smartcosmos.model.context.IAccount;
import net.smartcosmos.objects.builder.ObjectBuilder;
import net.smartcosmos.pojo.context.Account;
import net.smartcosmos.util.json.JsonGenerationView;
import net.smartcosmos.util.json.JsonUtil;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class CatalogTest
{
    private static ObjectMapper mapper = new ObjectMapper();

    static
    {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    }

    @Test
    public void testLibrary() throws IOException
    {
        String urn = "net.smartcosmos";
        String name = "Test Account";
        String description = "My test Description";

        IAccount testAccount = new Account();
        testAccount.setUrn(urn);
        testAccount.setName(name);
        testAccount.setDescription(description);
        testAccount.setActive(true);

        ILibrary library = new LibraryBuilder("Model Year 2015")
                .setAccount(testAccount)
                .setType("Vehicle")
                .setDescription("Collection of model year 2015 reference guides")

                .addShelf(new ShelfBuilder().
                        setName("Truck Models")
                        .setType("Truck")

                        .addBook(new BookBuilder()
                                .setType("F-150")
                                .setName("F-150 Service Guide")

                                .addChapter(new ChapterBuilder()
                                        .setName("Engine Service Guide")

                                        .addChapterSection(new ChapterSectionBuilder()
                                                .setName("Fluids")

                                                .addPage(new PageBuilder()
                                                        .setPageNumber(1)

                                                        .addPageEntry(new PageEntryBuilder()
                                                                .setObject(new ObjectBuilder("urn:uuid:abc123")
                                                                        .setType("foo")
                                                                        .build()))))

                                        .addChapterSection(new ChapterSectionBuilder()
                                                .setName("Belts")))

                                .addChapter(new ChapterBuilder()
                                        .setName("Interior Service Guide"))

                                .addChapter(new ChapterBuilder()
                                        .setName("Exterior Service Guide"))))

                .addShelf(new ShelfBuilder()
                                .setName("Car Models")
                                .setType("Car")

                                .addBook(new BookBuilder()
                                        .setType("Taurus")
                                        .setName("Taurus Service Guide"))
                )

                .build();


        String json = JsonUtil.toJson(library, JsonGenerationView.Full.class);
        assertTrue(json != null);

        System.out.println(json);
    }

}

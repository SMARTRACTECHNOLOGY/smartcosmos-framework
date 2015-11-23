package net.smartcosmos.platform.test;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.PojoValidator;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.NoFieldShadowingRule;
import com.openpojo.validation.rule.impl.NoNestedClassRule;
import com.openpojo.validation.rule.impl.NoPrimitivesRule;
import com.openpojo.validation.rule.impl.NoPublicFieldsExceptStaticFinalRule;
import com.openpojo.validation.rule.impl.NoStaticExceptFinalRule;
import com.openpojo.validation.rule.impl.SerializableMustHaveSerialVersionUIDRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.DefaultValuesNullTester;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import org.junit.Test;

public abstract class AbstractPojoTest
{

    protected abstract Class<?> getPojoClass();

    @Test
    public void validateSettersAndGetters()
    {
        PojoClass testPojo = PojoClassFactory.getPojoClass(getPojoClass());

        PojoValidator pojoValidator = new PojoValidator();
        // Lets make sure that we have a getter and a setter for every field defined.
        pojoValidator.addRule(new SetterMustExistRule());
        pojoValidator.addRule(new GetterMustExistRule());

        // Lets also validate that they are behaving as expected
        pojoValidator.addTester(new SetterTester());
        pojoValidator.addTester(new GetterTester());

        // We don't want any primitives in our Pojos.
        pojoValidator.addRule(new NoPrimitivesRule());

        // Pojo's should stay simple, don't allow nested classes, anonymous or declared.
        pojoValidator.addRule(new NoNestedClassRule());

        // Static fields must be final
        pojoValidator.addRule(new NoStaticExceptFinalRule());

        // Serializable must have serialVersionUID
        pojoValidator.addRule(new SerializableMustHaveSerialVersionUIDRule());

        // Don't shadow parent's field names.
        pojoValidator.addRule(new NoFieldShadowingRule());

        // What about public fields, use one of the following rules
        // allow them only if they are static and final.
        pojoValidator.addRule(new NoPublicFieldsExceptStaticFinalRule());

        // We don't want any default values to any fields - unless they are declared final or are primitive.
        pojoValidator.addTester(new DefaultValuesNullTester());

        // Start the Test
        pojoValidator.runValidation(testPojo);
    }

}

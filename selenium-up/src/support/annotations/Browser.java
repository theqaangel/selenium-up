package support.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import core.enums.Browsers;

/**
 * Annotation for browser definition
 * <p>
 * The Browser annotation can be set for test class as well as test method. In this way you can set
 * what will be the browser used for execution of all tests in particular test class and also to
 * specify one or several tests from this class to be executed in different browser.
 * <p>
 * 
 * @author Angel Tsvetkov <angel.tsvetkov@mail.com>
 * 
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Browser {

  Browsers value();
}

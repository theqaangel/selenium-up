package support.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for open url
 * <p>
 * This annotation specify what will be the URL opened in the browser when the test begins. It can
 * be set per test class as well as test method. In this way you can specify the default URL which
 * will be opened when the test begins on test class level and also to set different URL for some
 * test methods in this class.
 * <p>
 * 
 * @author Angel Tsvetkov <angel.tsvetkov@mail.com>
 * 
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface EntryPoint {

  String value();
}

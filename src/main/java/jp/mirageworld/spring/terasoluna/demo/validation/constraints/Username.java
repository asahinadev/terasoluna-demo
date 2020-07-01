package jp.mirageworld.spring.terasoluna.demo.validation.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern.Flag;

/**
 * The annotated {@code CharSequence} must match the specified regular
 * expression. The regular expression follows the Java regular expression
 * conventions see {@link java.util.regex.Pattern}.
 * <p>
 * Accepts {@code CharSequence}. {@code null} elements are considered valid.
 *
 * @author Emmanuel Bernard
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Repeatable(Username.List.class)
@Documented
@Constraint(validatedBy = {})
@Size(min = 8, max = 32)
@Pattern(regexp = "^[a-zA-Z0-9]+$")
public @interface Username {

	/**
	 * @return the regular expression to match
	 */
	@OverridesAttribute(constraint = Pattern.class)
	String regexp() default "^[a-zA-Z0-9]+$";

	/**
	 * @return array of {@code Flag}s considered when resolving the regular
	 *         expression
	 */
	@OverridesAttribute(constraint = Pattern.class)
	Flag[] flags() default {};

	/**
	 * @return the error message template
	 */
	@OverridesAttribute(constraint = Pattern.class)
	String message() default "{jp.mirageworld.spring.terasoluna.demo.validation.constraints.Username.message}";

	/**
	 * @return the groups the constraint belongs to
	 */
	@OverridesAttribute(constraint = Pattern.class)
	Class<?>[] groups() default {};

	/**
	 * @return the payload associated to the constraint
	 */
	@OverridesAttribute(constraint = Pattern.class)
	Class<? extends Payload>[] payload() default {};

	/**
	 * @return size the element must be higher or equal to
	 */
	@OverridesAttribute(constraint = Size.class)
	int min() default 8;

	/**
	 * @return size the element must be lower or equal to
	 */
	@OverridesAttribute(constraint = Size.class)
	int max() default 32;

	/**
	 * Defines several {@link Pattern} annotations on the same element.
	 *
	 * @see Pattern
	 */
	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
	@Retention(RUNTIME)
	@Documented
	@interface List {

		Username[] value();
	}
}

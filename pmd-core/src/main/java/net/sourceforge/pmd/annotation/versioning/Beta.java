/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.annotation.versioning;

/**
 * New APIs marked with this annotation at the class or method level are known to be not stable when used
 * (e.g. bug-prone, known issues, cannot still be fully tested). This serves as a word of caution that using this
 * API may have bugs when being used.
 * <p>
 * An API does not have needed to be on {@link Experimental} before to use this annotation.
 * </p>
 * <p>
 * If it is decided that an API should have the annotation removed, after the release or merge to the code base which
 * includes that removal, the annotation cannot be added again to the API, implying that the API is considered stable.
 * </p>
 * <p>
 * This annotation must be used with a justified criterion and judged by other contributors or owners, meaning that it
 * must not be added because of laziness for testing, for example.
 * </p>
 * <p>Use {@link Experimental} instead if is known that the API will change in any way.</p>
 */
public @interface Beta {
}

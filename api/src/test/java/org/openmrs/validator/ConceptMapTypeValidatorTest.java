/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.openmrs.ConceptMapType;
import org.openmrs.test.jupiter.BaseContextSensitiveTest;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

/**
 * Contains tests methods for the {@link ConceptMapTypeValidator}
 */
public class ConceptMapTypeValidatorTest extends BaseContextSensitiveTest {
	
	/**
	 * @see ConceptMapTypeValidator#validate(Object,Errors)
	 */
	@Test
	public void validate_shouldFailIfTheConceptMapTypeNameIsADuplicate() {
		ConceptMapType mapType = new ConceptMapType();
		mapType.setName("is a");
		Errors errors = new BindException(mapType, "mapType");
		new ConceptMapTypeValidator().validate(mapType, errors);
		assertEquals(true, errors.hasFieldErrors("name"));
	}
	
	/**
	 * @see ConceptMapTypeValidator#validate(Object,Errors)
	 */
	@Test
	public void validate_shouldFailIfTheConceptMapTypeObjectIsNull() {
		Errors errors = new BindException(new ConceptMapType(), "mapType");
		assertThrows(IllegalArgumentException.class, () ->  new ConceptMapTypeValidator().validate(null, errors));
	}
	
	/**
	 * @see ConceptMapTypeValidator#validate(Object,Errors)
	 */
	@Test
	public void validate_shouldFailIfTheNameIsAWhiteSpaceCharacter() {
		ConceptMapType mapType = new ConceptMapType();
		mapType.setName(" ");
		Errors errors = new BindException(mapType, "mapType");
		new ConceptMapTypeValidator().validate(mapType, errors);
		assertEquals(true, errors.hasFieldErrors("name"));
	}
	
	/**
	 * @see ConceptMapTypeValidator#validate(Object,Errors)
	 */
	@Test
	public void validate_shouldFailIfTheNameIsAnEmptyString() {
		ConceptMapType mapType = new ConceptMapType();
		mapType.setName("");
		Errors errors = new BindException(mapType, "mapType");
		new ConceptMapTypeValidator().validate(mapType, errors);
		assertEquals(true, errors.hasFieldErrors("name"));
	}
	
	/**
	 * @see ConceptMapTypeValidator#validate(Object,Errors)
	 */
	@Test
	public void validate_shouldFailIfTheNameIsNull() {
		ConceptMapType mapType = new ConceptMapType();
		Errors errors = new BindException(mapType, "mapType");
		new ConceptMapTypeValidator().validate(mapType, errors);
		assertEquals(true, errors.hasFieldErrors("name"));
	}
	
	/**
	 * @see ConceptMapTypeValidator#validate(Object,Errors)
	 */
	@Test
	public void validate_shouldPassIfTheNameIsUniqueAmongstAllConceptMapTypeNames() {
		ConceptMapType mapType = new ConceptMapType();
		mapType.setName("unique-name");
		Errors errors = new BindException(mapType, "mapType");
		new ConceptMapTypeValidator().validate(mapType, errors);
		assertEquals(false, errors.hasErrors());
	}
	
	/**
	 * @see ConceptMapTypeValidator#validate(Object,Errors)
	 */
	@Test
	public void validate_shouldPassValidationIfFieldLengthsAreCorrect() {
		ConceptMapType mapType = new ConceptMapType();
		mapType.setName("unique-name");
		mapType.setDescription("Description");
		mapType.setRetireReason("RetireReason");
		Errors errors = new BindException(mapType, "mapType");
		new ConceptMapTypeValidator().validate(mapType, errors);
		assertEquals(false, errors.hasErrors());
	}
	
	/**
	 * @see ConceptMapTypeValidator#validate(Object,Errors)
	 */
	@Test
	public void validate_shouldFailValidationIfFieldLengthsAreNotCorrect() {
		ConceptMapType mapType = new ConceptMapType();
		mapType.setName("unique-name");
		mapType
		        .setDescription("too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text");
		mapType
		        .setRetireReason("too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text too long text");
		Errors errors = new BindException(mapType, "mapType");
		new ConceptMapTypeValidator().validate(mapType, errors);
		assertEquals(true, errors.hasFieldErrors("description"));
		assertEquals(true, errors.hasFieldErrors("retireReason"));
	}
}

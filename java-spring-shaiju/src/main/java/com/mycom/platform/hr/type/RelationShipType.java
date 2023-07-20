package com.mycom.platform.hr.type;

/**
 * The Enum RelationShipType.
 */
public enum RelationShipType {
    
    HUSBAND("HUSBAND"),
	WIFE("WIFE"),
	FATHER("FATHER");
		
    /** The relationIdentifer. */
	private final String relationIdentifer;
	
	/**
	 * Instantiates a new RelationShipType.
	 *
	 */
	private RelationShipType(String relationIdentifer) {
		this.relationIdentifer = relationIdentifer;
	}

	/**
	 * Gets the relationIdentifer.
	 *
	 * @return the relationIdentifer
	 */
	public String getRelationIdentifer() {
		return relationIdentifer;
	}
	
}

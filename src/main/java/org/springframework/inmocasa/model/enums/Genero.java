package org.springframework.inmocasa.model.enums;

public enum Genero {

	FEMENINO ("Femenino"), 
	MASCULINO("Masculino"),
	OTRO("Otro"), 
	PREFIERO_NO_DECIRLO("Prefiero no decirlo");
	
	  private String displayName;

	  private Genero(String displayName) {
	    this.displayName = displayName;
	  }

	  public String getDisplayName() {
	    return displayName;
	  }

}

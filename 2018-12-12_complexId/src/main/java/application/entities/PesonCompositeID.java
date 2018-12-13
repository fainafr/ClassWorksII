package application.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
@EqualsAndHashCode

@Embeddable
public class PesonCompositeID implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String firstName;
	String lastName;
	
}

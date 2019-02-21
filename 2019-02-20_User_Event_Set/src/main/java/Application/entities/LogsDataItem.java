package Application.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "logsdata")
public class LogsDataItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //TODO: generation strategy for the logs; 
	private Long id; 
	@ManyToOne //Unidirectional;
	private UserItem user; 
	private LocalDate date;
	private LocalTime time;
	private UserActions action;
	private String description;

	public enum UserActions {
		EVENTSUBSCRIBE, EVENTEDIT, EVENTCANCEL, EVENTREAD, COMMENTLEFT, REGISTERED, LOGON, WATCHEDPROFILE,
		STATUS_CHANGE, EMAIL_CHANGE, ADDRESS_CHANGE;
	}
}

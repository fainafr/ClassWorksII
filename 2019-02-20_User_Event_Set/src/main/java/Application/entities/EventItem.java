package Application.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import Application.entities.values.EventRatingValue;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "eventlist")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = { "userItemsGuestsOfEvents", "feedBackItems" })
public class EventItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDate date;
	private LocalTime time;
	private String nameOfEvent;
	private EventRatingValue rating;

	@ManyToOne
	@JsonManagedReference
	private KichenTypeItem kichenTypeItem;

	@Enumerated(EnumType.STRING)
	private EventStatus Status;

	@ManyToOne 
	@JoinColumn(nullable = false) // temporary true due to User remove method requiring to remove the owner from Event;
	@JsonBackReference
	private UserItem userItemOwner;

	@ManyToOne
	@JsonBackReference
	private AddressItem addressItem;

	@ManyToMany
	@JsonBackReference
	private List<UserItem> userItemsGuestsOfEvents = new ArrayList<>();

	@OneToMany(mappedBy = "eventItem", cascade = CascadeType.ALL) // All feedBacks of event
	@JsonManagedReference
	private List<FeedBackItem> feedBackItems = new ArrayList<>();

	public enum EventStatus {
		CREATED, PENDING, COMPLETE, CANCELED
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		System.out.println("EQUALS CALLED");
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventItem other = (EventItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

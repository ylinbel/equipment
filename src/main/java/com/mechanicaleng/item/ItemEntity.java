package com.mechanicaleng.item;

import com.mechanicaleng.location.LocationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "item")
public class ItemEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(unique=true)
	private String serial; //serial number - QR code

	@Enumerated(value = EnumType.STRING)
	private StatusEnum statusEnum; //to specify if the item is in available, not available or damaged.

	private String setName; // name of the set

//	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@ManyToOne(fetch=FetchType.EAGER)
	private LocationEntity location;

	private String category;
//
//	private User current user;
//
//	private Time timeToReturn;
/*
	borrow date

	return date

	time period
 */




	public static ItemEntity fromDto(ItemDto itemDto) {
		return ItemEntity.builder().name(itemDto.getName()).serial(itemDto.getSerial()).statusEnum(itemDto.getStatusEnum()).setName(itemDto.getSetName()).category(itemDto.getCategory()).location(itemDto.getLocation()).build();
	}

	public ItemDto toDto() {
		return ItemDto.builder().name(this.getName()).serial(this.getSerial()).statusEnum(this.getStatusEnum()).setName(this.getSetName()).location(this.getLocation()).category(this.getCategory()).build();
	}

	public void updateFromDto(ItemDto itemDto) {
		this.setSetName(itemDto.getSetName());
		this.setStatusEnum(itemDto.getStatusEnum());
		this.setName(itemDto.getName());
		this.setSerial(itemDto.getSerial());
		this.setLocation(itemDto.getLocation());
		this.setCategory(itemDto.getCategory());
	}
}

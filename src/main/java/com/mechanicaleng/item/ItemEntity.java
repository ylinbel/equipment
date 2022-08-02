package com.mechanicaleng.item;

import com.mechanicaleng.location.LocationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

	private String serial; //serial number - QR code

	@Enumerated(value = EnumType.STRING)
	private StatusEnum statusEnum; //to specify if the item is in available, not available or damaged.

	private String setNum; // name of the set

	@ManyToOne(cascade = CascadeType.ALL)
	private LocationEntity location;

//    private String Location;
//
//    private String Category;


	public static ItemEntity fromDto(ItemDto itemDto) {
		return ItemEntity.builder().name(itemDto.getName()).serial(itemDto.getSerial()).statusEnum(itemDto.getStatusEnum()).setNum(itemDto.getSet()).build();
	}

	public ItemDto toDto() {
		return ItemDto.builder().name(this.getName()).serial(this.getSerial()).statusEnum(this.getStatusEnum()).set(this.getSetNum()).build();
	}

	public void updateFromDto(ItemDto itemDto) {
		this.setSetNum(itemDto.getSet());
		this.setStatusEnum(itemDto.getStatusEnum());
		this.setName(itemDto.getName());
		this.setSerial(itemDto.getSerial());
	}
}

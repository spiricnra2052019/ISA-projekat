package src.main.java.com.ftn.isa.model;

@Entity(name="UserVisitHistory")
public class UserVisitHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique=true, nullable = false)
	private Long id;
	
	@Column(name = "bloodType", nullable = false)
	private String bloodType;
	
	@Column(name = "quantity", nullable = false)
	private float quantity;
	
	@Column(name = "numberOfUsedEquipment", nullable = false)
	private int numberOfUsedEquipment;
	
	@Column(name = "description", nullable = false)
	private String description;
}

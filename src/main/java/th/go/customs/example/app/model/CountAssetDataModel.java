package th.go.customs.example.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "count_asset_data")
@Data
public class CountAssetDataModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 43683626587153361L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "number_totoal_count")
	private int numberTotoalCount;
	
	@Column(name = "total_count")
	private int totalCount;

	@Column(name = "cost_center", length = 255)
	private String costCenter;

	@Column(name = "creator", length = 255)
	private String creator;
	
	@Column(name = "count_number", length = 255)
	private String countNumber;
	
	@Column(name = "count_date")
	private Date countDate;
	
	@Column(name = "normal_number")
	private int normalNumber;
	
	@Column(name = "worn_out_number")
	private int wornOutNumber;
	
	@Column(name = "repair_number")
	private int repairNumber;
	
	@Column(name = "create_by", length = 25)
	private String createBy;

	@Column(name = "create_date")
	private Date createDate = new Date();

	@Column(name = "update_by", length = 25)
	private String updateBy;

	@Column(name = "update_date")
	private Date updateDate;

}

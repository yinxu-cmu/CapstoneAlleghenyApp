package edu.cmu.allegheny.data;


/**
 * Class that holds information for row entries in the Device database
 * @author justinrich
 *
 */
public class Device {

	private String DeviceID="";
	private String StoreID = "";
	private String DeviceType = "";
	private String DeviceGroup = ""; 
	private String Make = "";
	private String Model = "";
	private String SerialNumber = "";
	private String InspectionCycle = ""; 
	private String Pump = "";
	private String Grade = "";
	private String Capacity = "";
	private String Remarks = "";
	private String OldDueDate = "";
	
	public Device(String DeviceID, String StoreID, String DeviceType,
			String DeviceGroup, String Make, String Model,
			String SerialNumber, String InspectionCycle, String Pump,
			String Grade, String Capacity, String Remarks, String OldDueDate){
		
		this.DeviceID = DeviceID;
		this.StoreID = StoreID;
		this.DeviceType = DeviceType;
		this.DeviceGroup = DeviceGroup;
		this.Make = Make;
		this.Model = Model;
		this.SerialNumber = SerialNumber;
		this.InspectionCycle = InspectionCycle;
		this.Pump = Pump;
		this.Grade = Grade;
		this.Capacity = Capacity;
		this.Remarks = Remarks;
		this.OldDueDate = OldDueDate;
	}
	
	public Device(){}
	
	//Setter Methods
	
	/**
	 * Sets the ID for the device
	 * @param DeviceID
	 */
	public void setDeviceID(String DeviceID){
		this.DeviceID = DeviceID;
	}
	
	/**
	 * Sets the store ID
	 * @param StoreID
	 */
	public void setStoreID(String StoreID){
		this.StoreID = StoreID;
	}
	
	/**
	 * Sets the device type
	 * @param DeviceType
	 */
	public void setDeviceType(String DeviceType){
		this.DeviceType = DeviceType;
	}
	
	/**
	 * Sets the DeviceGroup value of the object
	 * @param DeviceGroup
	 */
	public void setDeviceGroup(String DeviceGroup){
		this.DeviceGroup = DeviceGroup;
	}
	
	/**
	 * Sets the object's internal Make object
	 * @param Make
	 */
	public void setMake(String Make){
		this.Make=Make;
	}
	
	/**
	 * Sets the object's internal Model string object
	 * @param Model
	 */
	public void setModel(String Model){
		this.Model = Model;
	}
	
	/**
	 * Sets the value of the object's Serial Number
	 * @param SerialNumber
	 */
	public void setSerialNumber(String SerialNumber){
		this.SerialNumber = SerialNumber;
	}
	
	/**
	 * Sets the inspection cycle of the object
	 * @param InspectionCycle
	 */
	public void setInspectionCycle(String InspectionCycle){
		this.InspectionCycle = InspectionCycle;
	}
	
	/**
	 * Sets the object's pump ID
	 * @param Pump
	 */
	public void setPump (String Pump){
		this.Pump = Pump;
	}
	
	/**
	 * Sets the object's grade
	 * @param Grade
	 */
	public void setGrade(String Grade){
		this.Grade = Grade;
	}
	
	/**
	 * Sets the object's capacity
	 * @param Capacity
	 */
	public void setCapacity(String Capacity){
		this.Capacity = Capacity;
	}
	
	/**
	 * Sets the object's remarks
	 * @param Remarks
	 */
	public void setRemarks(String Remarks){
		this.Remarks = Remarks;
	}
	
	/**
	 * Set's the object's old due date
	 * @param OldDueDate
	 */
	public void setOldDueDate(String OldDueDate){
		this.OldDueDate = OldDueDate;
	}
	
	//Device Getter Methods
	
	/**
	 * Gets object's device ID
	 * @return
	 */
	public String getDeviceID(){
		return this.DeviceID;
	}
	
	/**
	 * Gets Device's store ID
	 * @return
	 */
	public String getStoreID(){
		return this.StoreID;
	}
	
	/**
	 * Gets the device's device type
	 * @return
	 */
	public String getDeviceType(){
		return this.DeviceType;
	}
	
	/**
	 * Gets the device's device group
	 * @return
	 */
	public String getDeviceGroup(){
		return this.DeviceGroup;
	}
	
	/**
	 * Gets the device's make
	 * @return
	 */
	public String getMake(){
		return this.Make;
	}
	
	/**
	 * Gets device's model
	 * @return
	 */
	public String getModel(){
		return this.Model;
	}
	
	/**
	 * Gets the device's serial number
	 * @return
	 */
	public String getSerialNumber(){
		return this.SerialNumber;
	}
	
	
	/**
	 * Gets the device's inspection cycle
	 * @return
	 */
	public String getInspectionCycle(){
		return this.InspectionCycle;
	}
	
	/**
	 * Gets the pump info for this device
	 * @return
	 */
	public String getPump(){
		return this.Pump;
	}
	
	
	/**
	 * Gets the grade of the device
	 * @return
	 */
	public String getGrade(){
		return this.Grade;
	}
	
	/**
	 * Gets the capacity of the device
	 * @return
	 */
	public String getCapacity(){
		return this.Capacity;
	}
	
	/**
	 * Gets the remarks of the device
	 * @return
	 */
	public String getRemarks(){
		return this.Remarks;
	}
	
	/**
	 * Gets the old due date for this device
	 * @return
	 */
	public String getOldDueDate(){
		return this.OldDueDate;
	}
	
	
	
}

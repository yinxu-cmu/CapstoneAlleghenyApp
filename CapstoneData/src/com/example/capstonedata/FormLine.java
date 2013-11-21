package com.example.capstonedata;

/**
 * @author justinrich
 *Class that holds data for form line items. Use to record information
 *to  FormHelper database.
 */
public class FormLine {

	//data for the line item
	private String StationID = "";
	private String Address = "";
	private String City = "";
	private String State = "";
	private String Zip = "";
	private String Phone = "";
	private String MakeOfPump = "";
	private String SerialNumber = "";
	private String PumpNumber = "";
	private String BrandOfGas = "";
	private String GallonsDrawn = "";
	private String ErrorSlow = "";
	private String ErrorFast = "";
	private String TolTable = "";
	private String ActionTaken = "";
	private String Remarks = "";
	
	
	/**
	 * Default Constructor
	 */
	public FormLine(){}
	
	
	/**
	 * Constructor that initiates class
	 * @param StationID
	 * @param Address
	 * @param City
	 * @param State
	 * @param Zip
	 * @param Phone
	 * @param MakeOfPump
	 * @param SerialNumber
	 * @param PumpNumber
	 * @param BrandOfGas
	 * @param GallonsDrawn
	 * @param ErrorSlow
	 * @param ErrorFast
	 * @param TolTable
	 * @param ActionTaken
	 * @param Remarks
	 */
	public FormLine(String StationID,String Address, String City,
			String State, String Zip, String Phone, String MakeOfPump,
			String SerialNumber, String PumpNumber,String BrandOfGas, 
			String GallonsDrawn,String ErrorSlow, String ErrorFast,
			String TolTable, String ActionTaken, String Remarks){
		
		this.StationID = StationID;
		this.Address = Address;
		this.City = City;
		this.State = State;
		this.Zip = Zip;
		this.Phone = Phone;
		this.MakeOfPump = MakeOfPump;
		this.SerialNumber = SerialNumber;
		this.PumpNumber = PumpNumber;
		this.BrandOfGas = BrandOfGas;
		this.GallonsDrawn = GallonsDrawn;
		this.ErrorSlow = ErrorSlow;
		this.ErrorFast = ErrorFast;
		this.TolTable = TolTable;
		this.ActionTaken = ActionTaken;
		this.Remarks = Remarks;
	}
	
	//Class setters
	/**
	 * Sets line item station ID
	 * @param StationID
	 */
	public void setStationID(String StationID){
		this.StationID= StationID;
	}
	
	/**
	 * Sets line item address
	 * @param Address
	 */
	public void setAddress(String Address){
		this.Address = Address;
	}
	
	/**
	 * Sets line item city
	 * @param City
	 */
	public void setCity(String City){
		this.City = City;
	}
	
	/**
	 * Sets line item state
	 * @param State
	 */
	public void setState(String State){
		this.State = State;
	}
	
	/**
	 * Sets line item zip
	 * @param Zip
	 */
	public void setZip(String Zip){
		this.Zip = Zip;
	}
	
	/**
	 * Sets line item phone
	 * @param Phone
	 */
	public void setPhone(String Phone){
		this.Phone = Phone;
	}
	
	/**
	 * Sets the line item's make of pump
	 * @param MakeOfPump
	 */
	public void setMakeOfPump(String MakeOfPump){
		this.MakeOfPump = MakeOfPump;
	}
	
	/**
	 * Sets line item serial number
	 * @param SerialNumber
	 */
	public void setSerialNumber(String SerialNumber){
		this.SerialNumber = SerialNumber;
	}
	
	/**
	 * Sets line item pump number
	 * @param PumpNumber
	 */
	public void setPumpNumber(String PumpNumber){
		this.PumpNumber = PumpNumber;
	}
	
	/**
	 * Sets line item brand of gas
	 * @param BrandOfGas
	 */
	public void setBrandOfGas(String BrandOfGas){
		this.BrandOfGas = BrandOfGas;
	}
	
	/**
	 * Sets line item gallons drawn
	 * @param GallonsDrawn
	 */
	public void setGallonsDrawn(String GallonsDrawn){
		this.GallonsDrawn = GallonsDrawn;
	}
	
	/**
	 * Sets line item error slow
	 * @param ErrorSlow
	 */
	public void setErrorSlow(String ErrorSlow){
		this.ErrorSlow = ErrorSlow;
	}
	
	/**
	 * Sets line item error fast
	 * @param ErrorFast
	 */
	public void setErrorFast(String ErrorFast){
		this.ErrorFast = ErrorFast;
	}
	
	/**
	 * Sets line item TolTable
	 * @param TolTable
	 */
	public void setTolTable(String TolTable){
		this.TolTable = TolTable;
	}
	
	/**
	 * Sets line item action taken
	 * @param ActionTaken
	 */
	public void setActionTaken(String ActionTaken){
		this.ActionTaken = ActionTaken;
	}
	
	/**
	 * Sets line item remarks
	 * @param Remarks
	 */
	public void setRemarks(String Remarks){
		this.Remarks = Remarks;
	}
	
	//sets line item getter functions
	
	/**
	 * Returns line item station id
	 * @return
	 */
	public String getStationID(){
		return this.StationID;
	}
	
	/**
	 * Returns line item address
	 * @return
	 */
	public String getAddress(){
		return this.Address;
	}
	
	/**
	 * Returns line item city
	 * @return
	 */
	public String getCity(){
		return this.City;
	}
	
	/**
	 * Returns line item state
	 * @return
	 */
	public String getState(){
		return this.State;
	}
	
	/**
	 * Returns line item zip
	 * @return
	 */
	public String getZip(){
		return this.Zip;
	}
	
	/**
	 * Returns line item phone
	 * @return
	 */
	public String getPhone(){
		return this.Phone;
	}
	
	/**
	 * Returns line item make of pump
	 * @return
	 */
	public String getMakeOfPump(){
		return this.MakeOfPump;
	}
	
	/**
	 * Returns line item serial number
	 * @return
	 */
	public String getSerialNumber(){
		return this.SerialNumber;
	}
	
	/**
	 * Returns line item pump number
	 * @return
	 */
	public String getPumpNumber(){
		return this.PumpNumber;
	}
	
	/**
	 * Returns line item brand of gas
	 * @return
	 */
	public String getBrandOfGas(){
		return this.BrandOfGas;
	}
	
	/**
	 * Returns line item gallons drawn
	 * @return
	 */
	public String getGallonsDrawn(){
		return this.GallonsDrawn;
	}
	
	/**
	 * Returns line item error slow
	 * @return
	 */
	public String getErrorSlow(){
		return this.ErrorSlow;
	}
	
	/**
	 * Returns line item error fast
	 * @return
	 */
	public String getErrorFast(){
		return this.ErrorFast;
	}
	
	/**
	 * Returns line item TolTable
	 * @return
	 */
	public String getTolTable(){
		return this.TolTable;
	}
	
	/**
	 * Returns line item action taken
	 * @return
	 */
	public String getActionTaken(){
		return this.ActionTaken;
	}
	
	/**
	 * Returns line item remarks
	 * @return
	 */
	public String getRemarks(){
		return this.Remarks;
	}
	
	
}

/**
 * 
 */
package edu.cmu.alleghenywm.data;

/**
 * @author Justin Rich
 * 
 */

public class Store {

	private String StoreID = " ";
	private String StoreName = " ";
	private String Address = " ";
	private String MailingCity = " ";
	private String State = " ";
	private String Zip = " ";
	private String Municipality = " ";
	private String CorpID = " ";
	private String Neighborhood = " ";
	private String BusinessPhone = " ";
	private String AlternatePhone = " ";
	private String Price_Verification = " ";
	private String Price_VerDueDate = " ";
	private String FuelDispenser = " ";
	private String FuelDueDate = " ";
	private String Scale = " ";
	private String ScaleDueDate = " ";
	private String Timing = " ";
	private String TimingDueDate = " ";
	private String MiscInspection = " ";
	private String MiscDueDate = " ";
	private String Fee = " ";
	private String OOB = " ";
	private String Remarks = " ";
	private String NewAddress = " ";
	private String NewMunicipality = " ";

	/**
	 * Constructor that creates a new Store object for database recording
	 * 
	 * @param StoreID
	 * @param StoreName
	 * @param Address
	 * @param MailingCity
	 * @param State
	 * @param Zip
	 * @param Municipality
	 * @param CorpID
	 * @param Neighborhood
	 * @param BusinessPhone
	 * @param AlternatePhone
	 * @param Price_Verification
	 * @param Price_VerDueDate
	 * @param FuelDispenser
	 * @param FuelDueDate
	 * @param Scale
	 * @param ScaleDueDate
	 * @param Timing
	 * @param TimingDueDate
	 * @param MiscInspection
	 * @param MiscDueDate
	 * @param Fee
	 * @param OOB
	 * @param Remarks
	 * @param NewAddress
	 * @param NewMunicipality
	 */
	public Store(String StoreID, String StoreName, String Address,
			String MailingCity, String State, String Zip, String Municipality,
			String CorpID, String Neighborhood, String BusinessPhone,
			String AlternatePhone, String Price_Verification,
			String Price_VerDueDate, String FuelDispenser, String FuelDueDate,
			String Scale, String ScaleDueDate, String Timing,
			String TimingDueDate, String MiscInspection, String MiscDueDate,
			String Fee, String OOB, String Remarks, String NewAddress,
			String NewMunicipality) {
		this.StoreID = StoreID;
		this.StoreName = StoreName;
		this.Address = Address;
		this.MailingCity = MailingCity;
		this.State = State;
		this.Zip = Zip;
		this.Municipality = Municipality;
		this.CorpID = CorpID;
		this.Neighborhood = Neighborhood;
		this.BusinessPhone = BusinessPhone;
		this.AlternatePhone = AlternatePhone;
		this.Price_Verification = Price_Verification;
		this.Price_VerDueDate = Price_VerDueDate;
		this.FuelDispenser = FuelDispenser;
		this.FuelDueDate = FuelDueDate;
		this.Scale = Scale;
		this.ScaleDueDate = ScaleDueDate;
		this.Timing = Timing;
		this.TimingDueDate = TimingDueDate;
		this.MiscInspection = MiscInspection;
		this.MiscDueDate = MiscDueDate;
		this.Fee = Fee;
		this.OOB = OOB;
		this.Remarks = Remarks;
		this.NewAddress = NewAddress;
		this.NewMunicipality = NewMunicipality;
	}

	/**
	 * Default constructor
	 */
	public Store() {

	}

	// Getter methods

	/**
	 * Gets store ID
	 * 
	 * @return
	 */
	public String getStoreID() {
		return StoreID;
	}

	/**
	 * Gets store name
	 * 
	 * @return
	 */
	public String getStoreName() {
		return StoreName;
	}

	/**
	 * Gets store address
	 * 
	 * @return
	 */
	public String getAddress() {
		return Address;
	}

	/**
	 * Gets store mailing city
	 * 
	 * @return
	 */
	public String getMailingCity() {
		return MailingCity;
	}

	/**
	 * Gets store state
	 * 
	 * @return
	 */
	public String getState() {
		return State;
	}

	/**
	 * Gets store zip code
	 * 
	 * @return
	 */
	public String getZip() {
		return Zip;
	}

	/**
	 * Gets the fuel due date
	 * 
	 * @return
	 */
	public String getFuelDueDate() {
		return FuelDueDate;
	}

	/**
	 * Gets the store's municipality
	 * 
	 * @return
	 */
	public String getMunicipality() {
		return Municipality;
	}

	/**
	 * Gets the store's CorpID
	 * 
	 * @return
	 */
	public String getCorpID() {
		return CorpID;
	}

	/**
	 * Gets the store's neighborhood
	 * 
	 * @return
	 */
	public String getNeighborhood() {
		return Neighborhood;
	}

	/**
	 * Gets the store's business phone
	 * 
	 * @return
	 */
	public String getBusinessPhone() {
		return BusinessPhone;
	}

	/**
	 * Gets the store's alternate phone
	 * 
	 * @return
	 */
	public String getAlternatePhone() {
		return AlternatePhone;
	}

	/**
	 * Returns the store's price verification
	 * 
	 * @return
	 */
	public String getPrice_Verification() {
		return Price_Verification;
	}

	/**
	 * Returns the store's price verification due date
	 * 
	 * @return
	 */
	public String getPrice_VerDueDate() {
		return Price_VerDueDate;
	}

	/**
	 * Gets the store's fuel dispenser
	 * 
	 * @return
	 */
	public String getFuelDispenser() {
		return FuelDispenser;
	}

	/**
	 * Gets the store's scale
	 * 
	 * @return
	 */
	public String getScale() {
		return Scale;
	}

	/**
	 * Gets the store's scale due date
	 * 
	 * @return
	 */
	public String getScaleDueDate() {
		return ScaleDueDate;
	}

	/**
	 * Gets the store's timing
	 * 
	 * @return
	 */
	public String getTiming() {
		return Timing;
	}

	/**
	 * Gets the store's timing due date
	 * 
	 * @return
	 */
	public String getTimingDueDate() {
		return TimingDueDate;
	}

	/**
	 * Returns the store's misc inspection info
	 * 
	 * @return
	 */
	public String getMiscInspection() {
		return MiscInspection;
	}

	/**
	 * Gets the store's misc inspection info's due date
	 * 
	 * @return
	 */
	public String getMiscDueDate() {
		return MiscDueDate;
	}

	/**
	 * Gets the store's fee
	 * 
	 * @return
	 */
	public String getFee() {
		return Fee;
	}

	/**
	 * Gets the store's OOB
	 * 
	 * @return
	 */
	public String getOOB() {
		return OOB;
	}

	/**
	 * Gets the store's Remarks
	 * 
	 * @return
	 */
	public String getRemarks() {
		return Remarks;
	}

	/**
	 * Gets the store's new address
	 * 
	 * @return
	 */
	public String getNewAddress() {
		return NewAddress;
	}

	/**
	 * Gets the store's new municipality
	 * 
	 * @return
	 */
	public String getNewMunicipality() {
		return NewMunicipality;
	}

	// Setter functions
	/**
	 * Sets the store's ID
	 * 
	 * @param StoreID
	 */
	public void setStoreID(String StoreID) {
		this.StoreID = StoreID;
	}

	/**
	 * Sets the store's name
	 * 
	 * @param StoreName
	 */
	public void setStoreName(String StoreName) {
		this.StoreName = StoreName;
	}

	/**
	 * Sets the store's address
	 * 
	 * @param Address
	 */
	public void setAddress(String Address) {
		this.Address = Address;
	}

	/**
	 * Sets the store's mailing city
	 * 
	 * @param MailingCity
	 */
	public void setMailingCity(String MailingCity) {
		this.MailingCity = MailingCity;
	}

	/**
	 * Sets the store's state
	 * 
	 * @param State
	 */
	public void setState(String State) {
		this.State = State;
	}

	/**
	 * Sets the store's zip
	 * 
	 * @param Zip
	 */
	public void setZip(String Zip) {
		this.Zip = Zip;
	}

	/**
	 * Sets the store's municipality
	 * 
	 * @param Municipality
	 */
	public void setMunicipality(String Municipality) {
		this.Municipality = Municipality;
	}

	/**
	 * Sets the store's CorpID
	 * 
	 * @param CorpID
	 */
	public void setCorpID(String CorpID) {
		this.CorpID = CorpID;
	}

	/**
	 * Sets the store's neighborhood
	 * 
	 * @param Neighborhood
	 */
	public void setNeighborhood(String Neighborhood) {
		this.Neighborhood = Neighborhood;
	}

	/**
	 * Sets the store's business phone
	 * 
	 * @param BusinessPhone
	 */
	public void setBusinesPhone(String BusinessPhone) {
		this.BusinessPhone = BusinessPhone;
	}

	/**
	 * Sets the store's alternate phone
	 * 
	 * @param AlternatePhone
	 */
	public void setAlternatePhone(String AlternatePhone) {
		this.AlternatePhone = AlternatePhone;
	}

	/**
	 * Sets the store's price verification
	 * 
	 * @param Price_Verification
	 */
	public void setPrice_Verification(String Price_Verification) {
		this.Price_Verification = Price_Verification;
	}

	/**
	 * Sets the store's Price_VerDueDate
	 * 
	 * @param Price_VerDueDate
	 */
	public void setPrice_VerDueDate(String Price_VerDueDate) {
		this.Price_VerDueDate = Price_VerDueDate;
	}

	/**
	 * Sets the store's fuel dispenser
	 * 
	 * @param FuelDispenser
	 */
	public void setFuelDispenser(String FuelDispenser) {
		this.FuelDispenser = FuelDispenser;
	}

	/**
	 * Sets the store's fuel due date
	 * 
	 * @param FuelDueDate
	 */
	public void setFuelDueDate(String FuelDueDate) {
		this.FuelDueDate = FuelDueDate;
	}

	/**
	 * Sets the store's scale
	 * 
	 * @param Scale
	 */
	public void setScale(String Scale) {
		this.Scale = Scale;
	}

	/**
	 * Gets the store's scale due date
	 * 
	 * @param ScaleDueDate
	 */
	public void setScaleDueDate(String ScaleDueDate) {
		this.ScaleDueDate = ScaleDueDate;
	}

	/**
	 * Sets the store's timing
	 * 
	 * @param Timing
	 */
	public void setTiming(String Timing) {
		this.Timing = Timing;
	}

	/**
	 * Sets the timing due date for the store
	 * 
	 * @param TimingDueDate
	 */
	public void setTimingDueDate(String TimingDueDate) {
		this.TimingDueDate = TimingDueDate;
	}

	/**
	 * Sets the misc inspection information for the store
	 * 
	 * @param MiscInspection
	 */
	public void setMiscInspection(String MiscInspection) {
		this.MiscInspection = MiscInspection;
	}

	/**
	 * Sets the misc due date for the store
	 * 
	 * @param MiscDueDate
	 */
	public void setMiscDueDate(String MiscDueDate) {
		this.MiscDueDate = MiscDueDate;
	}

	/**
	 * Sets the fee for the store
	 * 
	 * @param Fee
	 */
	public void setFee(String Fee) {
		this.Fee = Fee;
	}

	/**
	 * Sets the OOB for the store
	 * 
	 * @param OOB
	 */
	public void setOOB(String OOB) {
		this.OOB = OOB;
	}

	/**
	 * Sets remarks for the store
	 * 
	 * @param Remarks
	 */
	public void setRemarks(String Remarks) {
		this.Remarks = Remarks;
	}

	/**
	 * Sets new address for the stores
	 * 
	 * @param NewAddress
	 */
	public void setNewAddress(String NewAddress) {
		this.NewAddress = NewAddress;
	}

	/**
	 * Sets the new municipality for the store
	 * 
	 * @param NewMunicipality
	 */
	public void setNewMunicipality(String NewMunicipality) {
		this.NewMunicipality = NewMunicipality;
	}

}
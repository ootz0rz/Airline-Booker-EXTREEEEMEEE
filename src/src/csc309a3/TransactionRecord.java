package csc309a3;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * A single transaction of the User buying some ticket...
 * 
 * @author Hardeep
 */
public class TransactionRecord {
	/* user transation data */
	
	public Date trDate;
	public Calendar trCC;
	
	public String FirstName = "";
	public String LastName = "";
	
	public int StreetNum = 0;
	public String StreetAddr = "";
	
	public String City = "";
	public PROVINCES Province = PROVINCES.Ontario;
	public String Postal = "";
	public String Phone = "";
	public String CCnum = "";
	public String CCexp = "";
	
	public static enum PROVINCES {
		Alberta,
		BC,
		Manitoba,
		NewBrunswick,
		Newfoundland,
		NWTerritories,
		NovaScotia,
		Nunavut,
		Ontario,
		PEI,
		Quebec,
		Saskatchewan,
		Yukon
	}
	
	/* useful arrays */
	public static final HashMap<PROVINCES, String> intToProvince = new HashMap<PROVINCES, String>() {{ 
        put(PROVINCES.Alberta, "Alberta");
        put(PROVINCES.BC, "British Colombia");
        put(PROVINCES.Manitoba, "Manitoba");
        put(PROVINCES.NewBrunswick, "New Brunswick");
        put(PROVINCES.Newfoundland, "Newfoundland and Labrador");
        put(PROVINCES.NWTerritories, "Northwest Territories");
        put(PROVINCES.NovaScotia, "Nova Scotia");
        put(PROVINCES.Nunavut, "Nunavut");
        put(PROVINCES.Ontario, "Ontario");
        put(PROVINCES.PEI, "Prince Edward Island");
        put(PROVINCES.Quebec, "Québec");
        put(PROVINCES.Saskatchewan, "Saskatchewan");
        put(PROVINCES.Yukon, "Yukon");
    }};
    
	public static final HashMap<Integer, PROVINCES> intToProvinceEnum = new HashMap<Integer, PROVINCES>() {{ 
        put(0, PROVINCES.Alberta);
        put(1, PROVINCES.BC);
        put(2, PROVINCES.Manitoba);
        put(3, PROVINCES.NewBrunswick);
        put(4, PROVINCES.Newfoundland);
        put(5, PROVINCES.NWTerritories);
        put(6, PROVINCES.NovaScotia);
        put(7, PROVINCES.Nunavut);
        put(8, PROVINCES.Ontario);
        put(9, PROVINCES.PEI);
        put(10, PROVINCES.Quebec);
        put(11, PROVINCES.Saskatchewan);
        put(12, PROVINCES.Yukon);
    }};
}

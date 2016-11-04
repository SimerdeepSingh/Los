package los.valiance.com.los.Helper;

/**
 * Created by admin2 on 12-10-2016.
 */
public class Constants {
    public static String rooturl="http://192.168.1.19:3322/LoanService.svc";
    public static String statusUrl=rooturl+"/GetAllLeadStatus";//";
    public static String titleUrl=rooturl+"/GetTitleTypes";//";
    public static String stateUrl=rooturl+"/GetAllState";//";
    public static String sourceUrl=rooturl+"/GetAllLeadSource";//";
    public static String salesOfficerUrl=rooturl+"/GetAllSalesOfficers";//";
    public static String teamManagerUrl=rooturl+"/GetAllTeamManagers";
    public static String loanTypeUrl=rooturl+"/GetAllLoanType";//";
    public static String loanPurposeUrl=rooturl+"/GetAllLoanPurposeType";//";
    public static String typeOFEmployeeUrl=rooturl+"/GetAllEmployeeType";//";
    public static String relationshipUrl=rooturl+"/GetAllRelationshipType";//";
    public static String cityUrl=rooturl+"/GetAllDistrictsWithStateID";//";

    public static String saveUrl=rooturl+"/AddUpdateLoanLeadEnquiry";

    public static String statusTable="leadStatusList";
    public static String titleTable="leadTitleList";
    public static String stateTable="StateList";
    public static String cityTable="CityList";
    public static String sourceTable="SourceList";
    public static String salesOfficerTable="SalesOfficerList";
    public static String teamManagerTable="TeamManagerList";
    public static String loanTypeTable="LoanTypeList";
    public static String loanPurposeTable="LoanPurposeList";
    public static String leadTypeTable="TypeList";
    public static String relationshipTable="RelationshipList";
    public static String createLeadTable="LeadCreation";
    public static String verificationTable="VerificationList";

   public static int IMAGE_SIZE = 96 * 120;
    public static int updateTime=30000;
}

## Test task for Sixtynames

### Task 1:

1. Run [XSLTTester.java](src%2Fmain%2Fjava%2Fcom%2Fsixtynames%2FXSLTTester.java)
2. A result folder will be created in the resources folder with files corresponding to the following files: 
   * [addRequestedAmount.xsl](src%2Fmain%2Fresources%2FaddRequestedAmount.xsl)
   * [addGuarantorDetails.xsl](src%2Fmain%2Fresources%2FaddGuarantorDetails.xsl)
   * [addINNToLoaners.xsl](src%2Fmain%2Fresources%2FaddINNToLoaners.xsl)

### Task 2:

1. Run [DataValidator.java](src%2Fmain%2Fjava%2Fcom%2Fsixtynames%2FDataValidator.java)
2. If you want to fail validation change this fields in [data.xml](src%2Fmain%2Fresources%2Fdata.xml):
    * Age to 86 or above
    * RequestedAmount < or > MinAmount or MaxAmount
    * RequestedTerm < or > MinTerm or MaxTerm
    * INN contains more then 10 digits

### Task 3:

1. Package __task3__
2. There are tests for MeetingManager [MeetingManagerTest.java](src%2Ftest%2Fjava%2Fcom%2Fsixtynames%2Ftask3%2FMeetingManagerTest.java)
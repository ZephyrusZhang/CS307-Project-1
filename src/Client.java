public class Client {
    public static void main(String[] args) {
        DataManipulation dm = new DataFactory().createDataManipulation(args[0]);
        dm.openDatasource();
//        dm.executeDDL();
//        dm.truncateAllTables();
        DataImport.importDataByEntity(dm);
        dm.closeDatasource();
    }
}

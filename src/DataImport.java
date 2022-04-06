import entity.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

public class DataImport {

    public static void importDataByEntity(DataManipulation dm) {
        try {
//            dm.dropAllData();

            BufferedReader reader = new BufferedReader(new FileReader("contract_info.csv"));
            String line;
            String[] data;
            Product product;
            Model model;
            Location location;
            Salesman salesman;
            Enterprise enterprise;
            Contract contract;
            Orders orders;
            String[] salesmanName;
            String[] directorName;

            reader.readLine();
            long startTime = System.currentTimeMillis();
            while ((line = reader.readLine()) != null) {
                data = line.split(",");

                product = new Product(data[6], data[8]);
                dm.addOneProductByEntity(product);

                model = new Model(data[8], Integer.parseInt(data[9]), data[6]);
                dm.addOneModelByEntity(model);

                String[] countries = data[3].split("/");
                for (String country : countries) {
                    location = new Location(country, data[4]);
                    dm.addOneLocationByEntity(location);
                }

                salesmanName = data[15].split(" ");
                directorName = data[14].split(" ");
                if (data[3].equals("China")) {
                    salesman = new Salesman(salesmanName[1], salesmanName[0], data[16], data[17], Integer.parseInt(data[18]), data[19]);
                    contract = new Contract(data[0], data[11], directorName[1], directorName[0], data[1]);
                } else {
                    salesman = new Salesman(salesmanName[0], salesmanName[1], data[16], data[17], Integer.parseInt(data[18]), data[19]);
                    contract = new Contract(data[0], data[11], directorName[0], directorName[1], data[1]);
                }
                dm.addOneSalesmanByEntity(salesman);
                dm.addOneContractByEntity(contract);

                enterprise = new Enterprise(data[1], data[5], -1, data[2]);
                dm.addOneEnterpriseByEntity(enterprise);

                orders = new Orders(Integer.parseInt(data[10]), data[12], data[13], data[8], -1, data[0]);
                dm.addOneOrdersByEntity(orders);
            }
            dm.cleanData();
            long endTime = System.currentTimeMillis();
            long timeUsed = (endTime - startTime) / 1000;
            System.out.printf("Data import procedure cost %d s\n", timeUsed);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}

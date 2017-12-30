package client.ui.select;

import client.ClientStart;
import client.data.Contract;
import client.ui.signin.SignIn;
import client.util.ManageClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class SelectController extends ManageClient implements Initializable {

    public Text customerName;
    public ListView<String> messageListView;
    public ComboBox<String> subsidiaryListBox;
    public ComboBox<String> routeListBox;
    public ComboBox<String> timePriceBox;
    public ComboBox<String> insuranceBox;

    private String subsidiary_no;
    private String route_no;
    private String timePrice_no;
    private String guide_no;
    private String insurance_no;

    @FXML
    protected void customerInformation() {
        refreshMessageListView(connDB.getCustomerStr(customer.getCustomer_telNumber()));
    }

    @FXML
    protected void submit() {
        if (subsidiary_no.equals("") || route_no.equals("") || timePrice_no.equals("")) {
            JOptionPane.showMessageDialog(null, "please choose subsidiary, spot and time first");
            return;
        }
        // choose insurance
        int confirm = JOptionPane.showConfirmDialog(null, "are you sure to make this order?",
                "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == 0) {
            Contract contract = new Contract();
            contract.setCustomer_telNumber(customer.getCustomer_telNumber());
            contract.setSubsidiary_no(subsidiary_no);
            contract.setRoute_no(route_no);
            contract.setGuide_no(guide_no);
            contract.setTimePrice_no(timePrice_no);
            contract.setInsurance_no(insurance_no);
            if (connDB.addNewContract(contract)) {
                JOptionPane.showMessageDialog(null, "success to add new order");
            } else {
                JOptionPane.showMessageDialog(null, "fail to add new order");
            }
        }
    }

    @FXML
    protected void contractInformation() {
        messageListView.getItems().remove(0, messageListView.getItems().size());
        refreshMessageListView(connDB.getContract(customer));
    }

    @FXML
    protected void back() {
        SignIn signIn = new SignIn();
        try {
            signIn.init();
            signIn.start(ClientStart.getStage());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "fail to fallback to SignIn interface");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customerName.setText(customer.getCustomer_name());
        refreshMessageListView(FXCollections.observableArrayList());
        //refresh subsidiary comboBox
        if (!subsidiaryListBox.getItems().isEmpty()) {
            subsidiaryListBox.getItems().remove(0, subsidiaryListBox.getItems().size());
        }
        subsidiaryListBox.getItems().addAll(connDB.getSubsidiaryName().split(","));
        subsidiaryListBox.valueProperty().addListener((ov1, str11, str12) -> {
            subsidiary_no = connDB.getSubsidiaryNo(subsidiaryListBox.getSelectionModel().getSelectedItem());
            System.out.println(subsidiary_no);
            refreshMessageListView(connDB.getSubsidiary(subsidiary_no));
            // refresh route comboBox
            if (!routeListBox.getItems().isEmpty()) {
                routeListBox.getItems().remove(0, routeListBox.getItems().size());
            }
            routeListBox.getItems().addAll(connDB.getRouteName(subsidiary_no).split(","));
            routeListBox.valueProperty().addListener((ov2, str21, str22) -> {
                route_no = connDB.getRouteNo(routeListBox.getSelectionModel().getSelectedItem());
                System.out.println(route_no);
                refreshMessageListView(connDB.getRoute(route_no));
                // refresh timePrice comboBox
                if (!timePriceBox.getItems().isEmpty()) {
                    timePriceBox.getItems().remove(0, routeListBox.getItems().size());
                }
                timePriceBox.getItems().addAll(connDB.getTimePriceName(route_no).split(","));
                timePriceBox.valueProperty().addListener((ov3, str31, str32) -> {
                    String str = timePriceBox.getSelectionModel().getSelectedItem();
                    timePrice_no = str.substring(0, str.indexOf(":"));
                    System.out.println(timePrice_no);
                    guide_no = connDB.getGuideNo(timePrice_no);
                });
            });
        });

        if (!insuranceBox.getItems().isEmpty()) {
            insuranceBox.getItems().remove(0, insuranceBox.getItems().size());
        }
        insuranceBox.getItems().addAll(connDB.getInsuranceName());
        insuranceBox.valueProperty().addListener((ov4, str41, str42) -> {
            String str = insuranceBox.getSelectionModel().getSelectedItem();
            insurance_no = str.substring(0, str.indexOf(":"));
            System.out.println(insurance_no);
        });
    }

    private void refreshMessageListView(ObservableList<String> messageList) {
        messageListView.getItems().remove(0, messageListView.getItems().size());
        messageListView.setItems(messageList);
    }
}
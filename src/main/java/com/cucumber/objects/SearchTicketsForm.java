package com.cucumber.objects;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SearchTicketsForm {

    public String startStation = "Berlin";
    public String finishStation = "Hamburg";

    private By searchTicketsForm = By.xpath("//*[@class=\"where\"]");
    private By startStationInputField = By.xpath("//*[@id=\"search_start_station\"]");
    private By startStationFirstDropdownItem = By.xpath("/html/body/div[5]/ul/li[1]");
    private By finishStationInputField = By.xpath("//*[@id=\"search_finish_station\"]");
    private By finishStationFirstDropdownItem = By.xpath("/html/body/div[6]/ul/li[1]");
    private By departureInputField = By.xpath("//*[@id=\"search_departure_date_input\"]/input[2]");
    private By departureTomorrowButton = By.xpath("//*[@id=\"search_departure_date_datepicker\"]/div[1]/div[1]/a[2]");
    private By departureTimeHour = By.xpath("//*[@id=\"search_departure_time_hour\"]");
    private By departureDatepicker = By.xpath("//*[@id=\"search_departure_time_min\"]");
    private By departureTimeMinute = By.xpath("//*[@id=\"search_departure_time_min\"]");
    private By adultPassengerMinus = By.xpath("//*[@class=\"who\"]/ol/li[1]/div[1]/div[1]/div[1]/button");
    private By adultPassengerPlus = By.xpath("//*[@class=\"who\"]/ol/li[1]/div[1]/div[1]/div[2]/button");
    private By adultPassengersNumber = By.xpath("//*[@id=\"search_passengers_attributes_0_number\"]");
    private By seniorPassengerMinus = By.xpath("//*[@class=\"who\"]/ol/li[2]/div[1]/div[1]/div[1]/button");
    private By seniorPassengerPlus = By.xpath("//*[@class=\"who\"]/ol/li[2]/div[1]/div[1]/div[2]/button");
    private By seniorPassengersNumber = By.xpath("//*[@id=\"search_passengers_attributes_1_number\"]");
    private By youthPassengerMinus = By.xpath("//*[@class=\"who\"]/ol/li[3]/div[1]/div[1]/div[1]/button");
    private By youthPassengerPlus = By.xpath("//*[@class=\"who\"]/ol/li[3]/div[1]/div[1]/div[2]/button");
    private By youthPassengersNumber = By.xpath("//*[@id=\"search_passengers_attributes_2_number\"]");
    private By youthPassengerInputField = By.xpath("//*[@id=\"passenger_2_age_0\"]");
    private By findTrainsButton = By.xpath("//*[@id=\"new_search\"]/fieldset[4]/ol/li[2]");

    public void userCanSeeSearchForm() {
        open("https://loco2.com/");
        $(searchTicketsForm).waitUntil(visible, 5000);
    }

    public void userCanSelectStartStationFromDropdown() {
        $(startStationInputField).setValue(startStation);
        $(startStationFirstDropdownItem).shouldBe(visible);
        $(startStationFirstDropdownItem).click();
        $(startStationInputField).shouldHave(value(startStation));
    }

    public void userCanSelectFinishStationFromDropdown(){
        $(finishStationInputField).setValue(finishStation);
        $(finishStationFirstDropdownItem).shouldBe(visible);
        //First item is selected
        $(finishStationFirstDropdownItem).click();
        $(finishStationInputField).shouldHave(value(finishStation));
    }

    public void userCanSetTripDateAndTime(){
        $(departureInputField).click();
        $(departureDatepicker).shouldBe(visible);
        $(departureTomorrowButton).click();
        $(departureTimeHour).selectOptionByValue("16");
        $(departureTimeHour).shouldHave(text("16"));
        $(departureTimeMinute).selectOptionByValue("30");
        $(departureTimeMinute).shouldHave(text("30"));
    }

    public void userCanSelectPassengers(){
        $(adultPassengersNumber).shouldHave(value("1"));
        $(adultPassengerMinus).click();
        $(adultPassengersNumber).shouldHave(value("0"));
        $(adultPassengerMinus).shouldBe(disabled);
        $(adultPassengerPlus).click();
        $(adultPassengersNumber).shouldHave(value("1"));
        $(seniorPassengersNumber).shouldHave(value("0"));
        $(seniorPassengerMinus).shouldBe(disabled);
        $(seniorPassengersNumber).shouldHave(value("0"));
        $(seniorPassengerPlus).click();
        $(seniorPassengersNumber).shouldHave(value("1"));
        $(youthPassengerInputField).shouldNotBe(visible);
        $(youthPassengersNumber).shouldHave(value("0"));
        $(youthPassengerInputField).shouldBe(disabled);
        $(youthPassengerPlus).click();
        $(youthPassengersNumber).shouldHave(value("1"));
        $(youthPassengerInputField).shouldBe(visible).setValue("10");
        $(youthPassengerMinus).click();
        $(youthPassengerMinus).shouldBe(disabled);
    }

    public SearchResultsPage userCanClickFindButton() {
        $(findTrainsButton).click();
        return new SearchResultsPage();
    }

}

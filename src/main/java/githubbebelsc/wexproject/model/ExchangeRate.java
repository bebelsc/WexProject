package githubbebelsc.wexproject.model;

import java.util.Date;

public class ExchangeRate {
    
    private Date record_date;
    private String country;
    private String currency;
    private String country_currency_desc;
    private double exchange_rate;
    private Date effective_date;
    private int src_line_nbr;
    private int record_fiscal_year;
    private int record_fiscal_quarter;
    private int record_calendar_year;
    private int record_calendar_quarter;
    private int record_calendar_month;
    private int record_calendar_day;
    
    public Date getRecord_date() {
        return record_date;
    }
    public void setRecord_date(Date record_date) {
        this.record_date = record_date;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public String getCountry_currency_desc() {
        return country_currency_desc;
    }
    public void setCountry_currency_desc(String country_currency_desc) {
        this.country_currency_desc = country_currency_desc;
    }
    public double getExchange_rate() {
        return exchange_rate;
    }
    public void setExchange_rate(double exchange_rate) {
        this.exchange_rate = exchange_rate;
    }
    public Date getEffective_date() {
        return effective_date;
    }
    public void setEffective_date(Date effective_date) {
        this.effective_date = effective_date;
    }
    public int getSrc_line_nbr() {
        return src_line_nbr;
    }
    public void setSrc_line_nbr(int src_line_nbr) {
        this.src_line_nbr = src_line_nbr;
    }
    public int getRecord_fiscal_year() {
        return record_fiscal_year;
    }
    public void setRecord_fiscal_year(int record_fiscal_year) {
        this.record_fiscal_year = record_fiscal_year;
    }
    public int getRecord_fiscal_quarter() {
        return record_fiscal_quarter;
    }
    public void setRecord_fiscal_quarter(int record_fiscal_quarter) {
        this.record_fiscal_quarter = record_fiscal_quarter;
    }
    public int getRecord_calendar_year() {
        return record_calendar_year;
    }
    public void setRecord_calendar_year(int record_calendar_year) {
        this.record_calendar_year = record_calendar_year;
    }
    public int getRecord_calendar_quarter() {
        return record_calendar_quarter;
    }
    public void setRecord_calendar_quarter(int record_calendar_quarter) {
        this.record_calendar_quarter = record_calendar_quarter;
    }
    public int getRecord_calendar_month() {
        return record_calendar_month;
    }
    public void setRecord_calendar_month(int record_calendar_month) {
        this.record_calendar_month = record_calendar_month;
    }
    public int getRecord_calendar_day() {
        return record_calendar_day;
    }
    public void setRecord_calendar_day(int record_calendar_day) {
        this.record_calendar_day = record_calendar_day;
    }

    public ExchangeRate() {
        
    }
}


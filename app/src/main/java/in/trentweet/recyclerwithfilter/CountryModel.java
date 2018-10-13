package in.trentweet.recyclerwithfilter;

public class CountryModel {

    private String countryName;
    private String countryCapitalName;
    private String countryNativeName;
    private String countryFlagUrl;

    public CountryModel(String countryName, String countryCapitalName,
                        String countryNativeName, String countryFlagUrl) {
        this.countryName = countryName;
        this.countryCapitalName = countryCapitalName;
        this.countryNativeName = countryNativeName;
        this.countryFlagUrl = countryFlagUrl;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryCapitalName() {
        return countryCapitalName;
    }

    public String getCountryNativeName() {
        return countryNativeName;
    }

    public String getCountryFlagUrl() {
        return countryFlagUrl;
    }
}

package eip.pepsi.com.demoapi.model;


public class Inventory {
    private String mtrl_uniq_id;
    private String brand_name;
    private String flvr_name;
    private String mfg_class_name;
    private String mtrl_desc;
    private String mtrl_id;
    private String mtlr_nm;
    private String mtrl_size;
    private String mtrl_status;
    private String mtrl_type;
    private String uom;
    private String uom_name;

    public Inventory(String mtrl_uniq_id, String brand_name, String flvr_name, String mfg_class_name, String mtrl_desc, String mtrl_id, String mtlr_nm, String mtrl_size, String mtrl_status, String mtrl_type, String uom, String uom_name) {
        this.mtrl_uniq_id = mtrl_uniq_id;
        this.brand_name = brand_name;
        this.flvr_name = flvr_name;
        this.mfg_class_name = mfg_class_name;
        this.mtrl_desc = mtrl_desc;
        this.mtrl_id = mtrl_id;
        this.mtlr_nm = mtlr_nm;
        this.mtrl_size = mtrl_size;
        this.mtrl_status = mtrl_status;
        this.mtrl_type = mtrl_type;
        this.uom = uom;
        this.uom_name = uom_name;
    }

    public String getMtrl_uniq_id() {
        return mtrl_uniq_id;
    }

    public void setMtrl_uniq_id(String mtrl_uniq_id) {
        this.mtrl_uniq_id = mtrl_uniq_id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getFlvr_name() {
        return flvr_name;
    }

    public void setFlvr_name(String flvr_name) {
        this.flvr_name = flvr_name;
    }

    public String getMfg_class_name() {
        return mfg_class_name;
    }

    public void setMfg_class_name(String mfg_class_name) {
        this.mfg_class_name = mfg_class_name;
    }

    public String getMtrl_desc() {
        return mtrl_desc;
    }

    public void setMtrl_desc(String mtrl_desc) {
        this.mtrl_desc = mtrl_desc;
    }

    public String getMtrl_id() {
        return mtrl_id;
    }

    public void setMtrl_id(String mtrl_id) {
        this.mtrl_id = mtrl_id;
    }

    public String getMtlr_nm() {
        return mtlr_nm;
    }

    public void setMtlr_nm(String mtlr_nm) {
        this.mtlr_nm = mtlr_nm;
    }

    public String getMtrl_size() {
        return mtrl_size;
    }

    public void setMtrl_size(String mtrl_size) {
        this.mtrl_size = mtrl_size;
    }

    public String getMtrl_status() {
        return mtrl_status;
    }

    public void setMtrl_status(String mtrl_status) {
        this.mtrl_status = mtrl_status;
    }

    public String getMtrl_type() {
        return mtrl_type;
    }

    public void setMtrl_type(String mtrl_type) {
        this.mtrl_type = mtrl_type;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getUom_name() {
        return uom_name;
    }

    public void setUom_name(String uom_name) {
        this.uom_name = uom_name;
    }
}

package org.arn.hdsscapture.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name="sociodemographic", indexes = {@Index(name="idx_socialgroup_uuid", columnList="socialgroup_uuid")})
public class Sociodemographic {
	
	
	@Id
    @Column
    public String socialgroup_uuid;
	
	@Column
    public String individual_uuid;

    @Column
    public String fw_uuid;

    @Column
    public String location_uuid;

    @Column
    public Date mnh03_formcompldat;

    @Column
    public Date insertDate;


    @Column
    public Integer form_comments_yn;

    @Column
    public String form_comments_txt;

    @Column
    public Date sd_obsstdat;

    @Column
    public Integer marital_scorres;
    @Column
    public Integer marital_age;
    @Column
    public Integer religion_scorres;
    @Column
    public String religion_spfy_scorres;
    @Column
    public Integer cethnic;
    @Column
    public String othr_trb_spfy_cethnic;
    @Column
    public String nth_trb_spfy_cethnic;
    @Column
    public Integer head_hh_fcorres;
    @Column
    public String head_hh_spfy_fcorres;
    @Column
    public Integer house_occ_tot_fcorres;
    @Column
    public Integer house_occ_lt5_fcorres;
    @Column
    public Integer house_occ_ge5_fcorres;
    @Column
    public Integer h2o_fcorres;
    @Column
    public String h2o_spfy_fcorres;
    @Column
    public Integer h2o_dist_fcorres;
    @Column
    public Integer h2o_hours_fcorres;
    @Column
    public Integer h2o_mins_fcorres;
    @Column
    public Integer h2o_prep_fcorres;
    @Column
    public Integer h2o_prep_spfy_fcorres_1;
    @Column
    public Integer h2o_prep_spfy_fcorres_2;
    @Column
    public Integer h2o_prep_spfy_fcorres_3;
    @Column
    public Integer h2o_prep_spfy_fcorres_4;
    @Column
    public Integer h2o_prep_spfy_fcorres_5;
    @Column
    public Integer toilet_fcorres;
    @Column
    public String toilet_spfy_fcorres;
    @Column
    public Integer toilet_loc_fcorres;
    @Column
    public String toilet_loc_spfy_fcorres;
    @Column
    public Integer toilet_share_fcorres;
    @Column
    public Integer toilet_share_num_fcorres;
    @Column
    public Integer ext_wall_fcorres;
    @Column
    public String ext_wall_spfy_fcorres;
    @Column
    public Integer floor_fcorres;
    @Column
    public String floor_spfy_fcorres;
    @Column
    public Integer roof_fcorres;
    @Column
    public String roof_spfy_fcorres;
    @Column
    public Integer electricity_fcorres;
    @Column
    public Integer solar_fcorres;
    @Column
    public Integer internet_fcorres;
    @Column
    public Integer landline_fcorres;
    @Column
    public Integer mobile_fcorres;
    @Column
    public Integer mobile_num_fcorres;
    @Column
    public Integer mobile_access_fcorres;
    @Column
    public Integer radio_fcorres;
    @Column
    public Integer radio_num_fcorres;
    @Column
    public Integer tv_fcorres;
    @Column
    public Integer tv_num_fcorres;
    @Column
    public Integer fridge_fcorres;
    @Column
    public Integer fridge_num_fcorres;
    @Column
    public Integer computer_fcorres;
    @Column
    public Integer computer_num_fcorres;
    @Column
    public Integer watch_fcorres;
    @Column
    public Integer watch_num_fcorres;
    @Column
    public Integer bike_fcorres;
    @Column
    public Integer bike_num_fcorres;
    @Column
    public Integer motorcycle_fcorres;
    @Column
    public Integer motorcycle_num_fcorres;
    @Column
    public Integer car_fcorres;
    @Column
    public Integer car_num_fcorres;
    @Column
    public Integer boat_fcorres;
    @Column
    public Integer boat_num_fcorres;
    @Column
    public Integer cart_fcorres;
    @Column
    public Integer cart_num_fcorres;
    @Column
    public Integer plough_fcorres;
    @Column
    public Integer plough_num_fcorres;
    @Column
    public Integer foam_matt_fcorres;
    @Column
    public Integer foam_matt_num_fcorres;
    @Column
    public Integer straw_matt_fcorres;
    @Column
    public Integer straw_matt_num_fcorres;
    @Column
    public Integer spring_matt_fcorres;
    @Column
    public Integer spring_matt_num_fcorres;
    @Column
    public Integer sofa_fcorres;
    @Column
    public Integer sofa_num_fcorres;
    @Column
    public Integer lantern_fcorres;
    @Column
    public Integer lantern_num_fcorres;
    @Column
    public Integer sew_fcorres;
    @Column
    public Integer sew_num_fcorres;
    @Column
    public Integer wash_fcorres;
    @Column
    public Integer wash_num_fcorres;
    @Column
    public Integer blender_fcorres;
    @Column
    public Integer blender_num_fcorres;
    @Column
    public Integer mosquito_net_fcorres;
    @Column
    public Integer mosquito_net_num_fcorres;
    @Column
    public Integer tricycles_fcorres;
    @Column
    public Integer tricycles_num_fcorres;
    @Column
    public Integer tables_fcorres;
    @Column
    public Integer tables_num_fcorres;
    @Column
    public Integer cabinets_fcorres;
    @Column
    public Integer cabinets_num_fcorres;
    @Column
    public Integer sat_dish_fcorres;
    @Column
    public Integer sat_dish_num_fcorres;
    @Column
    public Integer dvd_cd_fcorres;
    @Column
    public Integer dvd_cd_num_fcorres;
    @Column
    public Integer aircon_fcorres;
    @Column
    public Integer aircon_num_fcorres;
    @Column
    public Integer tractor_fcorres;
    @Column
    public Integer tractor_num_fcorres;
    @Column
    public Integer own_rent_scorres;
    @Column
    public String own_rent_spfy_scorres;
    @Column
    public Integer house_rooms_fcorres;
    @Column
    public Integer house_room_child_fcorres;
    @Column
    public Integer land_fcorres;
    @Column
    public Integer land_use_fcorres_1;
    @Column
    public Integer land_use_fcorres_2;
    @Column
    public Integer land_use_fcorres_3;
    @Column
    public Integer land_use_fcorres_4;
    @Column
    public Integer land_use_fcorres_5;
    @Column
    public Integer land_use_fcorres_88;
    @Column
    public String land_use_spfy_fcorres_88;
    @Column
    public Integer livestock_fcorres;
    @Column
    public Integer cattle_fcorres;
    @Column
    public Integer cattle_num_fcorres;
    @Column
    public Integer goat_fcorres;
    @Column
    public Integer goat_num_fcorres;
    @Column
    public Integer sheep_fcorres;
    @Column
    public Integer sheep_num_fcorres;
    @Column
    public Integer poultry_fcorres;
    @Column
    public Integer poultry_num_fcorres;
    @Column
    public Integer pig_fcorres;
    @Column
    public Integer pig_num_fcorres;
    @Column
    public Integer donkey_fcorres;
    @Column
    public Integer donkey_num_fcorres;
    @Column
    public Integer horse_fcorres;
    @Column
    public Integer horse_num_fcorres;
    @Column
    public Integer animal_othr_fcorres;
    @Column
    public String animal_othr_spfy_fcorres;
    @Column
    public Integer animal_othr_num_fcorres;
    @Column
    public Integer job_scorres;
    @Column
    public String job_salary_spfy_scorres;
    @Column
    public String job_smbus_spfy_scorres;
    @Column
    public String job_busown_spfy_scorres;
    @Column
    public String job_skilled_spfy_scorres;
    @Column
    public String job_unskilled_spfy_scorres;
    @Column
    public String job_othr_spfy_scorres;
    @Column
    public Integer ptr_scorres;
    @Column
    public String ptr_salary_spfy_scorres;
    @Column
    public String ptr_smbus_spfy_scorres;
    @Column
    public String ptr_busown_spfy_scorres;
    @Column
    public String ptr_skilled_spfy_scorres;
    @Column
    public String ptr_unskilled_spfy_scorres;
    @Column
    public String ptr_othr_spfy_scorres;
    @Column
    public Integer stove_fcorres;
    @Column
    public String stove_spfy_fcorres;
    @Column
    public Integer stove_fuel_fcorres_1;
    @Column
    public Integer stove_fuel_fcorres_2;
    @Column
    public Integer stove_fuel_fcorres_3;
    @Column
    public Integer stove_fuel_fcorres_4;
    @Column
    public Integer stove_fuel_fcorres_5;
    @Column
    public Integer stove_fuel_fcorres_6;
    @Column
    public Integer stove_fuel_fcorres_7;
    @Column
    public Integer stove_fuel_fcorres_8;
    @Column
    public Integer stove_fuel_fcorres_9;
    @Column
    public Integer stove_fuel_fcorres_10;
    @Column
    public Integer stove_fuel_fcorres_11;
    @Column
    public Integer stove_fuel_fcorres_12;
    @Column
    public Integer stove_fuel_fcorres_13;
    @Column
    public Integer stove_fuel_fcorres_14;
    @Column
    public Integer stove_fuel_fcorres_88;
    @Column
    public String stove_fuel_spfy_fcorres_88;
    @Column
    public Integer cooking_inside_fcorres;
    @Column
    public Integer cooking_room_fcorres;
    @Column
    public Integer cooking_loc_fcorres;
    @Column
    public Integer cooking_vent_fcorres;
    @Column
    public Integer smoke_oecoccur;
    @Column
    public Integer smoke_in_oecdosfrq;
    @Column
    public Integer smoke_hhold_oecoccur;
    @Column
    public Integer smoke_hhold_in_oecdosfrq;
    @Column
    public Integer chew_oecoccur;
    @Column
    public Integer chew_bnut_oecoccur;
    @Column
    public Integer drink_oecoccur;
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fw_uuid", referencedColumnName = "fw_uuid", insertable = false, updatable = false)
	private Fieldworker fieldworker;
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "individual_uuid", referencedColumnName = "individual_uuid", insertable = false, updatable = false)
	private Individual individual;
    
    @MapsId
	@OneToOne(optional = false)
	@JoinColumn(name = "socialgroup_uuid", referencedColumnName = "socialgroup_uuid", insertable = false, updatable = false)
	private Socialgroup socialgroup = new Socialgroup();
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "location_uuid", referencedColumnName = "location_uuid", insertable = false, updatable = false)
	private Location location;
    
    public Sociodemographic() {}
    
	
	public String getIndividual_uuid() {
		return individual_uuid;
	}
	public void setIndividual_uuid(String individual_uuid) {
		this.individual_uuid = individual_uuid;
	}
	public String getSocialgroup_uuid() {
		return socialgroup_uuid;
	}
	public void setSocialgroup_uuid(String socialgroup_uuid) {
		this.socialgroup_uuid = socialgroup_uuid;
	}
	public String getFw_uuid() {
		return fw_uuid;
	}
	public void setFw_uuid(String fw_uuid) {
		this.fw_uuid = fw_uuid;
	}
	public String getLocation_uuid() {
		return location_uuid;
	}
	public void setLocation_uuid(String location_uuid) {
		this.location_uuid = location_uuid;
	}
	public Date getMnh03_formcompldat() {
		return mnh03_formcompldat;
	}
	public void setMnh03_formcompldat(Date mnh03_formcompldat) {
		this.mnh03_formcompldat = mnh03_formcompldat;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public Integer getForm_comments_yn() {
		return form_comments_yn;
	}
	public void setForm_comments_yn(Integer form_comments_yn) {
		this.form_comments_yn = form_comments_yn;
	}
	public String getForm_comments_txt() {
		return form_comments_txt;
	}
	public void setForm_comments_txt(String form_comments_txt) {
		this.form_comments_txt = form_comments_txt;
	}
	public Date getSd_obsstdat() {
		return sd_obsstdat;
	}
	public void setSd_obsstdat(Date sd_obsstdat) {
		this.sd_obsstdat = sd_obsstdat;
	}
	public Integer getMarital_scorres() {
		return marital_scorres;
	}
	public void setMarital_scorres(Integer marital_scorres) {
		this.marital_scorres = marital_scorres;
	}
	public Integer getMarital_age() {
		return marital_age;
	}
	public void setMarital_age(Integer marital_age) {
		this.marital_age = marital_age;
	}
	public Integer getReligion_scorres() {
		return religion_scorres;
	}
	public void setReligion_scorres(Integer religion_scorres) {
		this.religion_scorres = religion_scorres;
	}
	public String getReligion_spfy_scorres() {
		return religion_spfy_scorres;
	}
	public void setReligion_spfy_scorres(String religion_spfy_scorres) {
		this.religion_spfy_scorres = religion_spfy_scorres;
	}
	public Integer getCethnic() {
		return cethnic;
	}
	public void setCethnic(Integer cethnic) {
		this.cethnic = cethnic;
	}
	public String getOthr_trb_spfy_cethnic() {
		return othr_trb_spfy_cethnic;
	}
	public void setOthr_trb_spfy_cethnic(String othr_trb_spfy_cethnic) {
		this.othr_trb_spfy_cethnic = othr_trb_spfy_cethnic;
	}
	public String getNth_trb_spfy_cethnic() {
		return nth_trb_spfy_cethnic;
	}
	public void setNth_trb_spfy_cethnic(String nth_trb_spfy_cethnic) {
		this.nth_trb_spfy_cethnic = nth_trb_spfy_cethnic;
	}
	public Integer getHead_hh_fcorres() {
		return head_hh_fcorres;
	}
	public void setHead_hh_fcorres(Integer head_hh_fcorres) {
		this.head_hh_fcorres = head_hh_fcorres;
	}
	public String getHead_hh_spfy_fcorres() {
		return head_hh_spfy_fcorres;
	}
	public void setHead_hh_spfy_fcorres(String head_hh_spfy_fcorres) {
		this.head_hh_spfy_fcorres = head_hh_spfy_fcorres;
	}
	public Integer getHouse_occ_tot_fcorres() {
		return house_occ_tot_fcorres;
	}
	public void setHouse_occ_tot_fcorres(Integer house_occ_tot_fcorres) {
		this.house_occ_tot_fcorres = house_occ_tot_fcorres;
	}
	public Integer getHouse_occ_lt5_fcorres() {
		return house_occ_lt5_fcorres;
	}
	public void setHouse_occ_lt5_fcorres(Integer house_occ_lt5_fcorres) {
		this.house_occ_lt5_fcorres = house_occ_lt5_fcorres;
	}
	public Integer getHouse_occ_ge5_fcorres() {
		return house_occ_ge5_fcorres;
	}
	public void setHouse_occ_ge5_fcorres(Integer house_occ_ge5_fcorres) {
		this.house_occ_ge5_fcorres = house_occ_ge5_fcorres;
	}
	public Integer getH2o_fcorres() {
		return h2o_fcorres;
	}
	public void setH2o_fcorres(Integer h2o_fcorres) {
		this.h2o_fcorres = h2o_fcorres;
	}
	public String getH2o_spfy_fcorres() {
		return h2o_spfy_fcorres;
	}
	public void setH2o_spfy_fcorres(String h2o_spfy_fcorres) {
		this.h2o_spfy_fcorres = h2o_spfy_fcorres;
	}
	public Integer getH2o_dist_fcorres() {
		return h2o_dist_fcorres;
	}
	public void setH2o_dist_fcorres(Integer h2o_dist_fcorres) {
		this.h2o_dist_fcorres = h2o_dist_fcorres;
	}
	public Integer getH2o_hours_fcorres() {
		return h2o_hours_fcorres;
	}
	public void setH2o_hours_fcorres(Integer h2o_hours_fcorres) {
		this.h2o_hours_fcorres = h2o_hours_fcorres;
	}
	public Integer getH2o_mins_fcorres() {
		return h2o_mins_fcorres;
	}
	public void setH2o_mins_fcorres(Integer h2o_mins_fcorres) {
		this.h2o_mins_fcorres = h2o_mins_fcorres;
	}
	public Integer getH2o_prep_fcorres() {
		return h2o_prep_fcorres;
	}
	public void setH2o_prep_fcorres(Integer h2o_prep_fcorres) {
		this.h2o_prep_fcorres = h2o_prep_fcorres;
	}
	public Integer getH2o_prep_spfy_fcorres_1() {
		return h2o_prep_spfy_fcorres_1;
	}
	public void setH2o_prep_spfy_fcorres_1(Integer h2o_prep_spfy_fcorres_1) {
		this.h2o_prep_spfy_fcorres_1 = h2o_prep_spfy_fcorres_1;
	}
	public Integer getH2o_prep_spfy_fcorres_2() {
		return h2o_prep_spfy_fcorres_2;
	}
	public void setH2o_prep_spfy_fcorres_2(Integer h2o_prep_spfy_fcorres_2) {
		this.h2o_prep_spfy_fcorres_2 = h2o_prep_spfy_fcorres_2;
	}
	public Integer getH2o_prep_spfy_fcorres_3() {
		return h2o_prep_spfy_fcorres_3;
	}
	public void setH2o_prep_spfy_fcorres_3(Integer h2o_prep_spfy_fcorres_3) {
		this.h2o_prep_spfy_fcorres_3 = h2o_prep_spfy_fcorres_3;
	}
	public Integer getH2o_prep_spfy_fcorres_4() {
		return h2o_prep_spfy_fcorres_4;
	}
	public void setH2o_prep_spfy_fcorres_4(Integer h2o_prep_spfy_fcorres_4) {
		this.h2o_prep_spfy_fcorres_4 = h2o_prep_spfy_fcorres_4;
	}
	public Integer getH2o_prep_spfy_fcorres_5() {
		return h2o_prep_spfy_fcorres_5;
	}
	public void setH2o_prep_spfy_fcorres_5(Integer h2o_prep_spfy_fcorres_5) {
		this.h2o_prep_spfy_fcorres_5 = h2o_prep_spfy_fcorres_5;
	}
	public Integer getToilet_fcorres() {
		return toilet_fcorres;
	}
	public void setToilet_fcorres(Integer toilet_fcorres) {
		this.toilet_fcorres = toilet_fcorres;
	}
	public String getToilet_spfy_fcorres() {
		return toilet_spfy_fcorres;
	}
	public void setToilet_spfy_fcorres(String toilet_spfy_fcorres) {
		this.toilet_spfy_fcorres = toilet_spfy_fcorres;
	}
	public Integer getToilet_loc_fcorres() {
		return toilet_loc_fcorres;
	}
	public void setToilet_loc_fcorres(Integer toilet_loc_fcorres) {
		this.toilet_loc_fcorres = toilet_loc_fcorres;
	}
	public String getToilet_loc_spfy_fcorres() {
		return toilet_loc_spfy_fcorres;
	}
	public void setToilet_loc_spfy_fcorres(String toilet_loc_spfy_fcorres) {
		this.toilet_loc_spfy_fcorres = toilet_loc_spfy_fcorres;
	}
	public Integer getToilet_share_fcorres() {
		return toilet_share_fcorres;
	}
	public void setToilet_share_fcorres(Integer toilet_share_fcorres) {
		this.toilet_share_fcorres = toilet_share_fcorres;
	}
	public Integer getToilet_share_num_fcorres() {
		return toilet_share_num_fcorres;
	}
	public void setToilet_share_num_fcorres(Integer toilet_share_num_fcorres) {
		this.toilet_share_num_fcorres = toilet_share_num_fcorres;
	}
	public Integer getExt_wall_fcorres() {
		return ext_wall_fcorres;
	}
	public void setExt_wall_fcorres(Integer ext_wall_fcorres) {
		this.ext_wall_fcorres = ext_wall_fcorres;
	}
	public String getExt_wall_spfy_fcorres() {
		return ext_wall_spfy_fcorres;
	}
	public void setExt_wall_spfy_fcorres(String ext_wall_spfy_fcorres) {
		this.ext_wall_spfy_fcorres = ext_wall_spfy_fcorres;
	}
	public Integer getFloor_fcorres() {
		return floor_fcorres;
	}
	public void setFloor_fcorres(Integer floor_fcorres) {
		this.floor_fcorres = floor_fcorres;
	}
	public String getFloor_spfy_fcorres() {
		return floor_spfy_fcorres;
	}
	public void setFloor_spfy_fcorres(String floor_spfy_fcorres) {
		this.floor_spfy_fcorres = floor_spfy_fcorres;
	}
	public Integer getRoof_fcorres() {
		return roof_fcorres;
	}
	public void setRoof_fcorres(Integer roof_fcorres) {
		this.roof_fcorres = roof_fcorres;
	}
	public String getRoof_spfy_fcorres() {
		return roof_spfy_fcorres;
	}
	public void setRoof_spfy_fcorres(String roof_spfy_fcorres) {
		this.roof_spfy_fcorres = roof_spfy_fcorres;
	}
	public Integer getElectricity_fcorres() {
		return electricity_fcorres;
	}
	public void setElectricity_fcorres(Integer electricity_fcorres) {
		this.electricity_fcorres = electricity_fcorres;
	}
	public Integer getSolar_fcorres() {
		return solar_fcorres;
	}
	public void setSolar_fcorres(Integer solar_fcorres) {
		this.solar_fcorres = solar_fcorres;
	}
	public Integer getInternet_fcorres() {
		return internet_fcorres;
	}
	public void setInternet_fcorres(Integer internet_fcorres) {
		this.internet_fcorres = internet_fcorres;
	}
	public Integer getLandline_fcorres() {
		return landline_fcorres;
	}
	public void setLandline_fcorres(Integer landline_fcorres) {
		this.landline_fcorres = landline_fcorres;
	}
	public Integer getMobile_fcorres() {
		return mobile_fcorres;
	}
	public void setMobile_fcorres(Integer mobile_fcorres) {
		this.mobile_fcorres = mobile_fcorres;
	}
	public Integer getMobile_num_fcorres() {
		return mobile_num_fcorres;
	}
	public void setMobile_num_fcorres(Integer mobile_num_fcorres) {
		this.mobile_num_fcorres = mobile_num_fcorres;
	}
	public Integer getMobile_access_fcorres() {
		return mobile_access_fcorres;
	}
	public void setMobile_access_fcorres(Integer mobile_access_fcorres) {
		this.mobile_access_fcorres = mobile_access_fcorres;
	}
	public Integer getRadio_fcorres() {
		return radio_fcorres;
	}
	public void setRadio_fcorres(Integer radio_fcorres) {
		this.radio_fcorres = radio_fcorres;
	}
	public Integer getRadio_num_fcorres() {
		return radio_num_fcorres;
	}
	public void setRadio_num_fcorres(Integer radio_num_fcorres) {
		this.radio_num_fcorres = radio_num_fcorres;
	}
	public Integer getTv_fcorres() {
		return tv_fcorres;
	}
	public void setTv_fcorres(Integer tv_fcorres) {
		this.tv_fcorres = tv_fcorres;
	}
	public Integer getTv_num_fcorres() {
		return tv_num_fcorres;
	}
	public void setTv_num_fcorres(Integer tv_num_fcorres) {
		this.tv_num_fcorres = tv_num_fcorres;
	}
	public Integer getFridge_fcorres() {
		return fridge_fcorres;
	}
	public void setFridge_fcorres(Integer fridge_fcorres) {
		this.fridge_fcorres = fridge_fcorres;
	}
	public Integer getFridge_num_fcorres() {
		return fridge_num_fcorres;
	}
	public void setFridge_num_fcorres(Integer fridge_num_fcorres) {
		this.fridge_num_fcorres = fridge_num_fcorres;
	}
	public Integer getComputer_fcorres() {
		return computer_fcorres;
	}
	public void setComputer_fcorres(Integer computer_fcorres) {
		this.computer_fcorres = computer_fcorres;
	}
	public Integer getComputer_num_fcorres() {
		return computer_num_fcorres;
	}
	public void setComputer_num_fcorres(Integer computer_num_fcorres) {
		this.computer_num_fcorres = computer_num_fcorres;
	}
	public Integer getWatch_fcorres() {
		return watch_fcorres;
	}
	public void setWatch_fcorres(Integer watch_fcorres) {
		this.watch_fcorres = watch_fcorres;
	}
	public Integer getWatch_num_fcorres() {
		return watch_num_fcorres;
	}
	public void setWatch_num_fcorres(Integer watch_num_fcorres) {
		this.watch_num_fcorres = watch_num_fcorres;
	}
	public Integer getBike_fcorres() {
		return bike_fcorres;
	}
	public void setBike_fcorres(Integer bike_fcorres) {
		this.bike_fcorres = bike_fcorres;
	}
	public Integer getBike_num_fcorres() {
		return bike_num_fcorres;
	}
	public void setBike_num_fcorres(Integer bike_num_fcorres) {
		this.bike_num_fcorres = bike_num_fcorres;
	}
	public Integer getMotorcycle_fcorres() {
		return motorcycle_fcorres;
	}
	public void setMotorcycle_fcorres(Integer motorcycle_fcorres) {
		this.motorcycle_fcorres = motorcycle_fcorres;
	}
	public Integer getMotorcycle_num_fcorres() {
		return motorcycle_num_fcorres;
	}
	public void setMotorcycle_num_fcorres(Integer motorcycle_num_fcorres) {
		this.motorcycle_num_fcorres = motorcycle_num_fcorres;
	}
	public Integer getCar_fcorres() {
		return car_fcorres;
	}
	public void setCar_fcorres(Integer car_fcorres) {
		this.car_fcorres = car_fcorres;
	}
	public Integer getCar_num_fcorres() {
		return car_num_fcorres;
	}
	public void setCar_num_fcorres(Integer car_num_fcorres) {
		this.car_num_fcorres = car_num_fcorres;
	}
	public Integer getBoat_fcorres() {
		return boat_fcorres;
	}
	public void setBoat_fcorres(Integer boat_fcorres) {
		this.boat_fcorres = boat_fcorres;
	}
	public Integer getBoat_num_fcorres() {
		return boat_num_fcorres;
	}
	public void setBoat_num_fcorres(Integer boat_num_fcorres) {
		this.boat_num_fcorres = boat_num_fcorres;
	}
	public Integer getCart_fcorres() {
		return cart_fcorres;
	}
	public void setCart_fcorres(Integer cart_fcorres) {
		this.cart_fcorres = cart_fcorres;
	}
	public Integer getCart_num_fcorres() {
		return cart_num_fcorres;
	}
	public void setCart_num_fcorres(Integer cart_num_fcorres) {
		this.cart_num_fcorres = cart_num_fcorres;
	}
	public Integer getPlough_fcorres() {
		return plough_fcorres;
	}
	public void setPlough_fcorres(Integer plough_fcorres) {
		this.plough_fcorres = plough_fcorres;
	}
	public Integer getPlough_num_fcorres() {
		return plough_num_fcorres;
	}
	public void setPlough_num_fcorres(Integer plough_num_fcorres) {
		this.plough_num_fcorres = plough_num_fcorres;
	}
	public Integer getFoam_matt_fcorres() {
		return foam_matt_fcorres;
	}
	public void setFoam_matt_fcorres(Integer foam_matt_fcorres) {
		this.foam_matt_fcorres = foam_matt_fcorres;
	}
	public Integer getFoam_matt_num_fcorres() {
		return foam_matt_num_fcorres;
	}
	public void setFoam_matt_num_fcorres(Integer foam_matt_num_fcorres) {
		this.foam_matt_num_fcorres = foam_matt_num_fcorres;
	}
	public Integer getStraw_matt_fcorres() {
		return straw_matt_fcorres;
	}
	public void setStraw_matt_fcorres(Integer straw_matt_fcorres) {
		this.straw_matt_fcorres = straw_matt_fcorres;
	}
	public Integer getStraw_matt_num_fcorres() {
		return straw_matt_num_fcorres;
	}
	public void setStraw_matt_num_fcorres(Integer straw_matt_num_fcorres) {
		this.straw_matt_num_fcorres = straw_matt_num_fcorres;
	}
	public Integer getSpring_matt_fcorres() {
		return spring_matt_fcorres;
	}
	public void setSpring_matt_fcorres(Integer spring_matt_fcorres) {
		this.spring_matt_fcorres = spring_matt_fcorres;
	}
	public Integer getSpring_matt_num_fcorres() {
		return spring_matt_num_fcorres;
	}
	public void setSpring_matt_num_fcorres(Integer spring_matt_num_fcorres) {
		this.spring_matt_num_fcorres = spring_matt_num_fcorres;
	}
	public Integer getSofa_fcorres() {
		return sofa_fcorres;
	}
	public void setSofa_fcorres(Integer sofa_fcorres) {
		this.sofa_fcorres = sofa_fcorres;
	}
	public Integer getSofa_num_fcorres() {
		return sofa_num_fcorres;
	}
	public void setSofa_num_fcorres(Integer sofa_num_fcorres) {
		this.sofa_num_fcorres = sofa_num_fcorres;
	}
	public Integer getLantern_fcorres() {
		return lantern_fcorres;
	}
	public void setLantern_fcorres(Integer lantern_fcorres) {
		this.lantern_fcorres = lantern_fcorres;
	}
	public Integer getLantern_num_fcorres() {
		return lantern_num_fcorres;
	}
	public void setLantern_num_fcorres(Integer lantern_num_fcorres) {
		this.lantern_num_fcorres = lantern_num_fcorres;
	}
	public Integer getSew_fcorres() {
		return sew_fcorres;
	}
	public void setSew_fcorres(Integer sew_fcorres) {
		this.sew_fcorres = sew_fcorres;
	}
	public Integer getSew_num_fcorres() {
		return sew_num_fcorres;
	}
	public void setSew_num_fcorres(Integer sew_num_fcorres) {
		this.sew_num_fcorres = sew_num_fcorres;
	}
	public Integer getWash_fcorres() {
		return wash_fcorres;
	}
	public void setWash_fcorres(Integer wash_fcorres) {
		this.wash_fcorres = wash_fcorres;
	}
	public Integer getWash_num_fcorres() {
		return wash_num_fcorres;
	}
	public void setWash_num_fcorres(Integer wash_num_fcorres) {
		this.wash_num_fcorres = wash_num_fcorres;
	}
	public Integer getBlender_fcorres() {
		return blender_fcorres;
	}
	public void setBlender_fcorres(Integer blender_fcorres) {
		this.blender_fcorres = blender_fcorres;
	}
	public Integer getBlender_num_fcorres() {
		return blender_num_fcorres;
	}
	public void setBlender_num_fcorres(Integer blender_num_fcorres) {
		this.blender_num_fcorres = blender_num_fcorres;
	}
	public Integer getMosquito_net_fcorres() {
		return mosquito_net_fcorres;
	}
	public void setMosquito_net_fcorres(Integer mosquito_net_fcorres) {
		this.mosquito_net_fcorres = mosquito_net_fcorres;
	}
	public Integer getMosquito_net_num_fcorres() {
		return mosquito_net_num_fcorres;
	}
	public void setMosquito_net_num_fcorres(Integer mosquito_net_num_fcorres) {
		this.mosquito_net_num_fcorres = mosquito_net_num_fcorres;
	}
	public Integer getTricycles_fcorres() {
		return tricycles_fcorres;
	}
	public void setTricycles_fcorres(Integer tricycles_fcorres) {
		this.tricycles_fcorres = tricycles_fcorres;
	}
	public Integer getTricycles_num_fcorres() {
		return tricycles_num_fcorres;
	}
	public void setTricycles_num_fcorres(Integer tricycles_num_fcorres) {
		this.tricycles_num_fcorres = tricycles_num_fcorres;
	}
	public Integer getTables_fcorres() {
		return tables_fcorres;
	}
	public void setTables_fcorres(Integer tables_fcorres) {
		this.tables_fcorres = tables_fcorres;
	}
	public Integer getTables_num_fcorres() {
		return tables_num_fcorres;
	}
	public void setTables_num_fcorres(Integer tables_num_fcorres) {
		this.tables_num_fcorres = tables_num_fcorres;
	}
	public Integer getCabinets_fcorres() {
		return cabinets_fcorres;
	}
	public void setCabinets_fcorres(Integer cabinets_fcorres) {
		this.cabinets_fcorres = cabinets_fcorres;
	}
	public Integer getCabinets_num_fcorres() {
		return cabinets_num_fcorres;
	}
	public void setCabinets_num_fcorres(Integer cabinets_num_fcorres) {
		this.cabinets_num_fcorres = cabinets_num_fcorres;
	}
	public Integer getSat_dish_fcorres() {
		return sat_dish_fcorres;
	}
	public void setSat_dish_fcorres(Integer sat_dish_fcorres) {
		this.sat_dish_fcorres = sat_dish_fcorres;
	}
	public Integer getSat_dish_num_fcorres() {
		return sat_dish_num_fcorres;
	}
	public void setSat_dish_num_fcorres(Integer sat_dish_num_fcorres) {
		this.sat_dish_num_fcorres = sat_dish_num_fcorres;
	}
	public Integer getDvd_cd_fcorres() {
		return dvd_cd_fcorres;
	}
	public void setDvd_cd_fcorres(Integer dvd_cd_fcorres) {
		this.dvd_cd_fcorres = dvd_cd_fcorres;
	}
	public Integer getDvd_cd_num_fcorres() {
		return dvd_cd_num_fcorres;
	}
	public void setDvd_cd_num_fcorres(Integer dvd_cd_num_fcorres) {
		this.dvd_cd_num_fcorres = dvd_cd_num_fcorres;
	}
	public Integer getAircon_fcorres() {
		return aircon_fcorres;
	}
	public void setAircon_fcorres(Integer aircon_fcorres) {
		this.aircon_fcorres = aircon_fcorres;
	}
	public Integer getAircon_num_fcorres() {
		return aircon_num_fcorres;
	}
	public void setAircon_num_fcorres(Integer aircon_num_fcorres) {
		this.aircon_num_fcorres = aircon_num_fcorres;
	}
	public Integer getTractor_fcorres() {
		return tractor_fcorres;
	}
	public void setTractor_fcorres(Integer tractor_fcorres) {
		this.tractor_fcorres = tractor_fcorres;
	}
	public Integer getTractor_num_fcorres() {
		return tractor_num_fcorres;
	}
	public void setTractor_num_fcorres(Integer tractor_num_fcorres) {
		this.tractor_num_fcorres = tractor_num_fcorres;
	}
	public Integer getOwn_rent_scorres() {
		return own_rent_scorres;
	}
	public void setOwn_rent_scorres(Integer own_rent_scorres) {
		this.own_rent_scorres = own_rent_scorres;
	}
	public String getOwn_rent_spfy_scorres() {
		return own_rent_spfy_scorres;
	}
	public void setOwn_rent_spfy_scorres(String own_rent_spfy_scorres) {
		this.own_rent_spfy_scorres = own_rent_spfy_scorres;
	}
	public Integer getHouse_rooms_fcorres() {
		return house_rooms_fcorres;
	}
	public void setHouse_rooms_fcorres(Integer house_rooms_fcorres) {
		this.house_rooms_fcorres = house_rooms_fcorres;
	}
	public Integer getHouse_room_child_fcorres() {
		return house_room_child_fcorres;
	}
	public void setHouse_room_child_fcorres(Integer house_room_child_fcorres) {
		this.house_room_child_fcorres = house_room_child_fcorres;
	}
	public Integer getLand_fcorres() {
		return land_fcorres;
	}
	public void setLand_fcorres(Integer land_fcorres) {
		this.land_fcorres = land_fcorres;
	}
	public Integer getLand_use_fcorres_1() {
		return land_use_fcorres_1;
	}
	public void setLand_use_fcorres_1(Integer land_use_fcorres_1) {
		this.land_use_fcorres_1 = land_use_fcorres_1;
	}
	public Integer getLand_use_fcorres_2() {
		return land_use_fcorres_2;
	}
	public void setLand_use_fcorres_2(Integer land_use_fcorres_2) {
		this.land_use_fcorres_2 = land_use_fcorres_2;
	}
	public Integer getLand_use_fcorres_3() {
		return land_use_fcorres_3;
	}
	public void setLand_use_fcorres_3(Integer land_use_fcorres_3) {
		this.land_use_fcorres_3 = land_use_fcorres_3;
	}
	public Integer getLand_use_fcorres_4() {
		return land_use_fcorres_4;
	}
	public void setLand_use_fcorres_4(Integer land_use_fcorres_4) {
		this.land_use_fcorres_4 = land_use_fcorres_4;
	}
	public Integer getLand_use_fcorres_5() {
		return land_use_fcorres_5;
	}
	public void setLand_use_fcorres_5(Integer land_use_fcorres_5) {
		this.land_use_fcorres_5 = land_use_fcorres_5;
	}
	public Integer getLand_use_fcorres_88() {
		return land_use_fcorres_88;
	}
	public void setLand_use_fcorres_88(Integer land_use_fcorres_88) {
		this.land_use_fcorres_88 = land_use_fcorres_88;
	}
	public String getLand_use_spfy_fcorres_88() {
		return land_use_spfy_fcorres_88;
	}
	public void setLand_use_spfy_fcorres_88(String land_use_spfy_fcorres_88) {
		this.land_use_spfy_fcorres_88 = land_use_spfy_fcorres_88;
	}
	public Integer getLivestock_fcorres() {
		return livestock_fcorres;
	}
	public void setLivestock_fcorres(Integer livestock_fcorres) {
		this.livestock_fcorres = livestock_fcorres;
	}
	public Integer getCattle_fcorres() {
		return cattle_fcorres;
	}
	public void setCattle_fcorres(Integer cattle_fcorres) {
		this.cattle_fcorres = cattle_fcorres;
	}
	public Integer getCattle_num_fcorres() {
		return cattle_num_fcorres;
	}
	public void setCattle_num_fcorres(Integer cattle_num_fcorres) {
		this.cattle_num_fcorres = cattle_num_fcorres;
	}
	public Integer getGoat_fcorres() {
		return goat_fcorres;
	}
	public void setGoat_fcorres(Integer goat_fcorres) {
		this.goat_fcorres = goat_fcorres;
	}
	public Integer getGoat_num_fcorres() {
		return goat_num_fcorres;
	}
	public void setGoat_num_fcorres(Integer goat_num_fcorres) {
		this.goat_num_fcorres = goat_num_fcorres;
	}
	public Integer getSheep_fcorres() {
		return sheep_fcorres;
	}
	public void setSheep_fcorres(Integer sheep_fcorres) {
		this.sheep_fcorres = sheep_fcorres;
	}
	public Integer getSheep_num_fcorres() {
		return sheep_num_fcorres;
	}
	public void setSheep_num_fcorres(Integer sheep_num_fcorres) {
		this.sheep_num_fcorres = sheep_num_fcorres;
	}
	public Integer getPoultry_fcorres() {
		return poultry_fcorres;
	}
	public void setPoultry_fcorres(Integer poultry_fcorres) {
		this.poultry_fcorres = poultry_fcorres;
	}
	public Integer getPoultry_num_fcorres() {
		return poultry_num_fcorres;
	}
	public void setPoultry_num_fcorres(Integer poultry_num_fcorres) {
		this.poultry_num_fcorres = poultry_num_fcorres;
	}
	public Integer getPig_fcorres() {
		return pig_fcorres;
	}
	public void setPig_fcorres(Integer pig_fcorres) {
		this.pig_fcorres = pig_fcorres;
	}
	public Integer getPig_num_fcorres() {
		return pig_num_fcorres;
	}
	public void setPig_num_fcorres(Integer pig_num_fcorres) {
		this.pig_num_fcorres = pig_num_fcorres;
	}
	public Integer getDonkey_fcorres() {
		return donkey_fcorres;
	}
	public void setDonkey_fcorres(Integer donkey_fcorres) {
		this.donkey_fcorres = donkey_fcorres;
	}
	public Integer getDonkey_num_fcorres() {
		return donkey_num_fcorres;
	}
	public void setDonkey_num_fcorres(Integer donkey_num_fcorres) {
		this.donkey_num_fcorres = donkey_num_fcorres;
	}
	public Integer getHorse_fcorres() {
		return horse_fcorres;
	}
	public void setHorse_fcorres(Integer horse_fcorres) {
		this.horse_fcorres = horse_fcorres;
	}
	public Integer getHorse_num_fcorres() {
		return horse_num_fcorres;
	}
	public void setHorse_num_fcorres(Integer horse_num_fcorres) {
		this.horse_num_fcorres = horse_num_fcorres;
	}
	public Integer getAnimal_othr_fcorres() {
		return animal_othr_fcorres;
	}
	public void setAnimal_othr_fcorres(Integer animal_othr_fcorres) {
		this.animal_othr_fcorres = animal_othr_fcorres;
	}
	public String getAnimal_othr_spfy_fcorres() {
		return animal_othr_spfy_fcorres;
	}
	public void setAnimal_othr_spfy_fcorres(String animal_othr_spfy_fcorres) {
		this.animal_othr_spfy_fcorres = animal_othr_spfy_fcorres;
	}
	public Integer getAnimal_othr_num_fcorres() {
		return animal_othr_num_fcorres;
	}
	public void setAnimal_othr_num_fcorres(Integer animal_othr_num_fcorres) {
		this.animal_othr_num_fcorres = animal_othr_num_fcorres;
	}
	public Integer getJob_scorres() {
		return job_scorres;
	}
	public void setJob_scorres(Integer job_scorres) {
		this.job_scorres = job_scorres;
	}
	public String getJob_salary_spfy_scorres() {
		return job_salary_spfy_scorres;
	}
	public void setJob_salary_spfy_scorres(String job_salary_spfy_scorres) {
		this.job_salary_spfy_scorres = job_salary_spfy_scorres;
	}
	public String getJob_smbus_spfy_scorres() {
		return job_smbus_spfy_scorres;
	}
	public void setJob_smbus_spfy_scorres(String job_smbus_spfy_scorres) {
		this.job_smbus_spfy_scorres = job_smbus_spfy_scorres;
	}
	public String getJob_busown_spfy_scorres() {
		return job_busown_spfy_scorres;
	}
	public void setJob_busown_spfy_scorres(String job_busown_spfy_scorres) {
		this.job_busown_spfy_scorres = job_busown_spfy_scorres;
	}
	public String getJob_skilled_spfy_scorres() {
		return job_skilled_spfy_scorres;
	}
	public void setJob_skilled_spfy_scorres(String job_skilled_spfy_scorres) {
		this.job_skilled_spfy_scorres = job_skilled_spfy_scorres;
	}
	public String getJob_unskilled_spfy_scorres() {
		return job_unskilled_spfy_scorres;
	}
	public void setJob_unskilled_spfy_scorres(String job_unskilled_spfy_scorres) {
		this.job_unskilled_spfy_scorres = job_unskilled_spfy_scorres;
	}
	public String getJob_othr_spfy_scorres() {
		return job_othr_spfy_scorres;
	}
	public void setJob_othr_spfy_scorres(String job_othr_spfy_scorres) {
		this.job_othr_spfy_scorres = job_othr_spfy_scorres;
	}
	public Integer getPtr_scorres() {
		return ptr_scorres;
	}
	public void setPtr_scorres(Integer ptr_scorres) {
		this.ptr_scorres = ptr_scorres;
	}
	public String getPtr_salary_spfy_scorres() {
		return ptr_salary_spfy_scorres;
	}
	public void setPtr_salary_spfy_scorres(String ptr_salary_spfy_scorres) {
		this.ptr_salary_spfy_scorres = ptr_salary_spfy_scorres;
	}
	public String getPtr_smbus_spfy_scorres() {
		return ptr_smbus_spfy_scorres;
	}
	public void setPtr_smbus_spfy_scorres(String ptr_smbus_spfy_scorres) {
		this.ptr_smbus_spfy_scorres = ptr_smbus_spfy_scorres;
	}
	public String getPtr_busown_spfy_scorres() {
		return ptr_busown_spfy_scorres;
	}
	public void setPtr_busown_spfy_scorres(String ptr_busown_spfy_scorres) {
		this.ptr_busown_spfy_scorres = ptr_busown_spfy_scorres;
	}
	public String getPtr_skilled_spfy_scorres() {
		return ptr_skilled_spfy_scorres;
	}
	public void setPtr_skilled_spfy_scorres(String ptr_skilled_spfy_scorres) {
		this.ptr_skilled_spfy_scorres = ptr_skilled_spfy_scorres;
	}
	public String getPtr_unskilled_spfy_scorres() {
		return ptr_unskilled_spfy_scorres;
	}
	public void setPtr_unskilled_spfy_scorres(String ptr_unskilled_spfy_scorres) {
		this.ptr_unskilled_spfy_scorres = ptr_unskilled_spfy_scorres;
	}
	public String getPtr_othr_spfy_scorres() {
		return ptr_othr_spfy_scorres;
	}
	public void setPtr_othr_spfy_scorres(String ptr_othr_spfy_scorres) {
		this.ptr_othr_spfy_scorres = ptr_othr_spfy_scorres;
	}
	public Integer getStove_fcorres() {
		return stove_fcorres;
	}
	public void setStove_fcorres(Integer stove_fcorres) {
		this.stove_fcorres = stove_fcorres;
	}
	public String getStove_spfy_fcorres() {
		return stove_spfy_fcorres;
	}
	public void setStove_spfy_fcorres(String stove_spfy_fcorres) {
		this.stove_spfy_fcorres = stove_spfy_fcorres;
	}
	public Integer getStove_fuel_fcorres_1() {
		return stove_fuel_fcorres_1;
	}
	public void setStove_fuel_fcorres_1(Integer stove_fuel_fcorres_1) {
		this.stove_fuel_fcorres_1 = stove_fuel_fcorres_1;
	}
	public Integer getStove_fuel_fcorres_2() {
		return stove_fuel_fcorres_2;
	}
	public void setStove_fuel_fcorres_2(Integer stove_fuel_fcorres_2) {
		this.stove_fuel_fcorres_2 = stove_fuel_fcorres_2;
	}
	public Integer getStove_fuel_fcorres_3() {
		return stove_fuel_fcorres_3;
	}
	public void setStove_fuel_fcorres_3(Integer stove_fuel_fcorres_3) {
		this.stove_fuel_fcorres_3 = stove_fuel_fcorres_3;
	}
	public Integer getStove_fuel_fcorres_4() {
		return stove_fuel_fcorres_4;
	}
	public void setStove_fuel_fcorres_4(Integer stove_fuel_fcorres_4) {
		this.stove_fuel_fcorres_4 = stove_fuel_fcorres_4;
	}
	public Integer getStove_fuel_fcorres_5() {
		return stove_fuel_fcorres_5;
	}
	public void setStove_fuel_fcorres_5(Integer stove_fuel_fcorres_5) {
		this.stove_fuel_fcorres_5 = stove_fuel_fcorres_5;
	}
	public Integer getStove_fuel_fcorres_6() {
		return stove_fuel_fcorres_6;
	}
	public void setStove_fuel_fcorres_6(Integer stove_fuel_fcorres_6) {
		this.stove_fuel_fcorres_6 = stove_fuel_fcorres_6;
	}
	public Integer getStove_fuel_fcorres_7() {
		return stove_fuel_fcorres_7;
	}
	public void setStove_fuel_fcorres_7(Integer stove_fuel_fcorres_7) {
		this.stove_fuel_fcorres_7 = stove_fuel_fcorres_7;
	}
	public Integer getStove_fuel_fcorres_8() {
		return stove_fuel_fcorres_8;
	}
	public void setStove_fuel_fcorres_8(Integer stove_fuel_fcorres_8) {
		this.stove_fuel_fcorres_8 = stove_fuel_fcorres_8;
	}
	public Integer getStove_fuel_fcorres_9() {
		return stove_fuel_fcorres_9;
	}
	public void setStove_fuel_fcorres_9(Integer stove_fuel_fcorres_9) {
		this.stove_fuel_fcorres_9 = stove_fuel_fcorres_9;
	}
	public Integer getStove_fuel_fcorres_10() {
		return stove_fuel_fcorres_10;
	}
	public void setStove_fuel_fcorres_10(Integer stove_fuel_fcorres_10) {
		this.stove_fuel_fcorres_10 = stove_fuel_fcorres_10;
	}
	public Integer getStove_fuel_fcorres_11() {
		return stove_fuel_fcorres_11;
	}
	public void setStove_fuel_fcorres_11(Integer stove_fuel_fcorres_11) {
		this.stove_fuel_fcorres_11 = stove_fuel_fcorres_11;
	}
	public Integer getStove_fuel_fcorres_12() {
		return stove_fuel_fcorres_12;
	}
	public void setStove_fuel_fcorres_12(Integer stove_fuel_fcorres_12) {
		this.stove_fuel_fcorres_12 = stove_fuel_fcorres_12;
	}
	public Integer getStove_fuel_fcorres_13() {
		return stove_fuel_fcorres_13;
	}
	public void setStove_fuel_fcorres_13(Integer stove_fuel_fcorres_13) {
		this.stove_fuel_fcorres_13 = stove_fuel_fcorres_13;
	}
	public Integer getStove_fuel_fcorres_14() {
		return stove_fuel_fcorres_14;
	}
	public void setStove_fuel_fcorres_14(Integer stove_fuel_fcorres_14) {
		this.stove_fuel_fcorres_14 = stove_fuel_fcorres_14;
	}
	public Integer getStove_fuel_fcorres_88() {
		return stove_fuel_fcorres_88;
	}
	public void setStove_fuel_fcorres_88(Integer stove_fuel_fcorres_88) {
		this.stove_fuel_fcorres_88 = stove_fuel_fcorres_88;
	}
	public String getStove_fuel_spfy_fcorres_88() {
		return stove_fuel_spfy_fcorres_88;
	}
	public void setStove_fuel_spfy_fcorres_88(String stove_fuel_spfy_fcorres_88) {
		this.stove_fuel_spfy_fcorres_88 = stove_fuel_spfy_fcorres_88;
	}
	public Integer getCooking_inside_fcorres() {
		return cooking_inside_fcorres;
	}
	public void setCooking_inside_fcorres(Integer cooking_inside_fcorres) {
		this.cooking_inside_fcorres = cooking_inside_fcorres;
	}
	public Integer getCooking_room_fcorres() {
		return cooking_room_fcorres;
	}
	public void setCooking_room_fcorres(Integer cooking_room_fcorres) {
		this.cooking_room_fcorres = cooking_room_fcorres;
	}
	public Integer getCooking_loc_fcorres() {
		return cooking_loc_fcorres;
	}
	public void setCooking_loc_fcorres(Integer cooking_loc_fcorres) {
		this.cooking_loc_fcorres = cooking_loc_fcorres;
	}
	public Integer getCooking_vent_fcorres() {
		return cooking_vent_fcorres;
	}
	public void setCooking_vent_fcorres(Integer cooking_vent_fcorres) {
		this.cooking_vent_fcorres = cooking_vent_fcorres;
	}
	public Integer getSmoke_oecoccur() {
		return smoke_oecoccur;
	}
	public void setSmoke_oecoccur(Integer smoke_oecoccur) {
		this.smoke_oecoccur = smoke_oecoccur;
	}
	public Integer getSmoke_in_oecdosfrq() {
		return smoke_in_oecdosfrq;
	}
	public void setSmoke_in_oecdosfrq(Integer smoke_in_oecdosfrq) {
		this.smoke_in_oecdosfrq = smoke_in_oecdosfrq;
	}
	public Integer getSmoke_hhold_oecoccur() {
		return smoke_hhold_oecoccur;
	}
	public void setSmoke_hhold_oecoccur(Integer smoke_hhold_oecoccur) {
		this.smoke_hhold_oecoccur = smoke_hhold_oecoccur;
	}
	public Integer getSmoke_hhold_in_oecdosfrq() {
		return smoke_hhold_in_oecdosfrq;
	}
	public void setSmoke_hhold_in_oecdosfrq(Integer smoke_hhold_in_oecdosfrq) {
		this.smoke_hhold_in_oecdosfrq = smoke_hhold_in_oecdosfrq;
	}
	public Integer getChew_oecoccur() {
		return chew_oecoccur;
	}
	public void setChew_oecoccur(Integer chew_oecoccur) {
		this.chew_oecoccur = chew_oecoccur;
	}
	public Integer getChew_bnut_oecoccur() {
		return chew_bnut_oecoccur;
	}
	public void setChew_bnut_oecoccur(Integer chew_bnut_oecoccur) {
		this.chew_bnut_oecoccur = chew_bnut_oecoccur;
	}
	public Integer getDrink_oecoccur() {
		return drink_oecoccur;
	}
	public void setDrink_oecoccur(Integer drink_oecoccur) {
		this.drink_oecoccur = drink_oecoccur;
	}
    
    

}

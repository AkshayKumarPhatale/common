package com.accenture.empportal.request;

import com.accenture.empportal.validation.GenderCheck;
import com.accenture.empportal.validation.RoleCheck;
import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author HANUMAN
 *
 */

public class EmployeeRequest {

	private Long empId;

	@NotBlank(message = "employeeName shouldnot be Empty")
	private String empName;

	@NotBlank
	@GenderCheck(message = "empGender shouldnot be Empty or Gender Must be MALE OR FEMALE")
	private String empGender;

	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@CreationTimestamp
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updatedAt;

	@NotBlank
	@RoleCheck(message = "Role must be Empty or Role must be  ASSOCIATE or SENIOR or TEAM_LEAD or MANAGER ")
	private String empRole;

	private Double empSalary;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate joinedDate;

	private String manager;

	private Long deptId;

	public EmployeeRequest() {

	}

	public EmployeeRequest(Long empId, @NotBlank(message = "employeeName shouldnot be Empty") String empName,
			@NotBlank String empGender, Date createdAt, Date updatedAt, @NotBlank String empRole, Double empSalary,
			LocalDate joinedDate, String manager, Long deptId) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empGender = empGender;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.empRole = empRole;
		this.empSalary = empSalary;
		this.joinedDate = joinedDate;
		this.manager = manager;
		this.deptId = deptId;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpGender() {
		return empGender;
	}

	public void setEmpGender(String empGender) {
		this.empGender = empGender;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getEmpRole() {
		return empRole;
	}

	public void setEmpRole(String empRole) {
		this.empRole = empRole;
	}

	public Double getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(Double empSalary) {
		this.empSalary = empSalary;
	}

	public LocalDate getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(LocalDate joinedDate) {
		this.joinedDate = joinedDate;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.empId == null) ? 0 : this.empId.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empGender=" + empGender + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + ", empRole=" + empRole + ", empSalary=" + empSalary + "]";
	}

}

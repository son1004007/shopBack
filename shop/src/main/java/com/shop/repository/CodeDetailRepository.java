package com.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shop.domain.CodeDetail;
import com.shop.domain.CodeDetailId;

public interface CodeDetailRepository extends JpaRepository<CodeDetail, CodeDetailId> {

	@Query("SELECT max(cd.sortSeq) FROM CodeDetail cd WHERE cd.groupCode = ?1")
	public List<Object[]> getMaxSortSeq(String groupCode);

	@Query("SELECT cd FROM CodeDetail cd WHERE cd.groupCode = ?1")
	public List<CodeDetail> getCodeList(String groupCode);
}

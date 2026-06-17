package com.suraj.projects.lovable_clone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suraj.projects.lovable_clone.entity.ProjectMember;
import com.suraj.projects.lovable_clone.entity.ProjectMemberId;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember,  ProjectMemberId> {

    
    List<ProjectMember> finbdByIdProjectId(Long projectId);


}

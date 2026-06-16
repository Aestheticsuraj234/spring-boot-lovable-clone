package com.suraj.projects.lovable_clone.service;

import com.suraj.projects.lovable_clone.dto.member.InviteMemberRequest;
import com.suraj.projects.lovable_clone.dto.member.MemberResponse;
import com.suraj.projects.lovable_clone.dto.member.UpdateMemberRoleRequest;
import com.suraj.projects.lovable_clone.entity.ProjectMember;

import java.util.List;

public interface ProjectMemberService {
    List<ProjectMember> getProjectMembers(Long projectId, Long userId);

    MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId);

    MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request, Long userId);

    MemberResponse deleteProjectMember(Long projectId, Long memberId, Long userId);
}

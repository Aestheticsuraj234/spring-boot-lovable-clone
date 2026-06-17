package com.suraj.projects.lovable_clone.service.impl;

import com.suraj.projects.lovable_clone.dto.member.InviteMemberRequest;
import com.suraj.projects.lovable_clone.dto.member.MemberResponse;
import com.suraj.projects.lovable_clone.dto.member.UpdateMemberRoleRequest;
import com.suraj.projects.lovable_clone.entity.Project;
import com.suraj.projects.lovable_clone.entity.ProjectMember;
import com.suraj.projects.lovable_clone.entity.ProjectMemberId;
import com.suraj.projects.lovable_clone.entity.User;
import com.suraj.projects.lovable_clone.mapper.ProjectMemberMapper;
import com.suraj.projects.lovable_clone.repository.ProjectMemberRepository;
import com.suraj.projects.lovable_clone.repository.ProjectRepository;
import com.suraj.projects.lovable_clone.repository.UserRepository;
import com.suraj.projects.lovable_clone.service.ProjectMemberService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProjectMemberServiceImpl implements ProjectMemberService {

    ProjectMemberRepository projectMemberRepository;
    ProjectRepository projectRepository;
    ProjectMemberMapper projectMemberMapper;
    UserRepository userRepository;

    @Override
    public List<MemberResponse> getProjectMembers(Long projectId, Long userId) {
        Project project = getAccessibleProjectById(projectId, userId);
        List<MemberResponse> memberResponseList = new ArrayList<>();

        memberResponseList.add(projectMemberMapper.toProjectMemberResponseFromOwner(project.getOwner()));

        memberResponseList.addAll(projectMemberRepository.finbdByIdProjectId(projectId).stream()
                .map(projectMemberMapper::toProjectMemberResponseFromMember).toList());
        return memberResponseList;
    }

    @Override
    public MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId) {
      Project project = getAccessibleProjectById(projectId, userId);

      if(!project.getOwner().getId().equals(userId)) {
        throw new RuntimeException("You are not the owner of this project");
      }

      User invitee = userRepository.findByEmail(request.email()).orElseThrow();

     if(invitee.getId().equals(userId)){
        throw new RuntimeException("You cannot invite yourself to the project");
     }

     ProjectMemberId projectMemberId = new ProjectMemberId(projectId, invitee.getId());

     if(projectMemberRepository.existsById(projectMemberId)){
        throw new RuntimeException("Cannot invite once again");
     }

     ProjectMember member = ProjectMember.builder()
     .id(projectMemberId)
     .project(project)
     .user(invitee)
     .projectRole(request.role())
     .invitedAt(Instant.now())
     .build();

     projectMemberRepository.save(member);

     return projectMemberMapper.toProjectMemberResponseFromMember(member);
   
    }

    @Override
    public MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request,
            Long userId) {
        Project project = getAccessibleProjectById(projectId, userId);

        if (!project.getOwner().getId().equals(userId)) {
            throw new RuntimeException("You are not the owner of this project");
        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
        ProjectMember projectMember = projectMemberRepository.findById(projectMemberId).orElseThrow();

        projectMember.setProjectRole(request.role());
        projectMemberRepository.save(projectMember);

        return projectMemberMapper.toProjectMemberResponseFromMember(projectMember);
    }

    @Override
    public MemberResponse deleteProjectMember(Long projectId, Long memberId, Long userId) {
        Project project = getAccessibleProjectById(projectId, userId);

        if (!project.getOwner().getId().equals(userId)) {
            throw new RuntimeException("You are not the owner of this project");
        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
        ProjectMember projectMember = projectMemberRepository.findById(projectMemberId).orElseThrow();

        MemberResponse response = projectMemberMapper.toProjectMemberResponseFromMember(projectMember);
        projectMemberRepository.delete(projectMember);

        return response;
    }

    // Internal Functions
    private Project getAccessibleProjectById(Long projectId, Long userId) {
        return projectRepository.findAccessibleProjectById(projectId, userId).orElseThrow();
    }
}

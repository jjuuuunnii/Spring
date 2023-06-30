package practice.prac.member;

public interface MemberRepository {
    void save(Member member);
    Member findMember(Long memberId);
}

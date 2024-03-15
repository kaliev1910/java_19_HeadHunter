package com.example.java_19_headhunter.dao.interfaces;
import com.example.java_19_headhunter.models.Experience;
import java.util.List;

public interface ExperienceDao {
    // for creating a new Experience
    void  insert(Experience experience);

    // for updating existing Experience
    void  update(Experience experience);

    // for retrieving related Experience entities by resume_id
    List<Experience> findByResumeId(int resumeId);

    // for deleting Experience entities by resume_id
    void deleteByResumeId(int resumeId);
}

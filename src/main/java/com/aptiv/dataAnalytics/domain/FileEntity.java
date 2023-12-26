package com.aptiv.dataAnalytics.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name="files")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileId;
    private String fileName;
    private String fileType;
    @Lob
    @Column(columnDefinition="LONGBLOB")
    private byte[] data;
    private String fileDownloadUri;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinator_id", referencedColumnName = "id")
    private Coordinator coordinator;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shift_leader_id", referencedColumnName = "id")
    private ShiftLeader shiftLeader;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;


    public FileEntity( String fileId,String fileName, String fileType, byte[] data, String fileDownloadUri) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.fileDownloadUri= fileDownloadUri;
    }
}

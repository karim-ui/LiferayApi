package com.Liferaysend.liferaysend.repository;

import com.Liferaysend.liferaysend.model.fichier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileUploadRepository extends JpaRepository<fichier,String> {


}

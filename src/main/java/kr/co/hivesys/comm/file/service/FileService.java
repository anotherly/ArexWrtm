package kr.co.hivesys.comm.file.service;

import java.util.List;

import kr.co.hivesys.comm.file.vo.FileVo;

public interface FileService {
	public List<FileVo> selectFileList(FileVo inputVo);
	public void insertFile (List<FileVo> inputVo);
}

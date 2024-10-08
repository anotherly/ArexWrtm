package kr.co.hivesys.comm.file.mapper;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.co.hivesys.comm.file.vo.FileVo;

@Mapper("fileMapper")
public interface FileMapper {
	public List<FileVo> selectFileList(FileVo inputVo);
	public void insertFile (List<FileVo> inputVo);
}

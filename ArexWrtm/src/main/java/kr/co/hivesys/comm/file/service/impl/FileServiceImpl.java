package kr.co.hivesys.comm.file.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.hivesys.comm.file.mapper.FileMapper;
import kr.co.hivesys.comm.file.service.FileService;
import kr.co.hivesys.comm.file.vo.FileVo;

@Service("fileService")
public class FileServiceImpl implements FileService{
	@Resource(name="fileMapper")
	private FileMapper fileMapper;

	@Override
	public List<FileVo> selectFileList(FileVo inputVo) {
		return fileMapper.selectFileList(inputVo);
	}

	@Override
	public void insertFile(List<FileVo> inputVo) {
		fileMapper.insertFile(inputVo);
	}
}

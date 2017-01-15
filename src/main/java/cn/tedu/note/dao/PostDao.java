package cn.tedu.note.dao;

import cn.tedu.note.entity.Post;

public interface PostDao {
	
	Post findPostById(Integer id);
}

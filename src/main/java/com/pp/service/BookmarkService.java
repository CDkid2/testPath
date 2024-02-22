package com.pp.service;

import com.pp.domain.Bookmark;
import com.pp.domain.Des;
import com.pp.domain.User;
import com.pp.repository.BookmarkRepository;
import com.pp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface BookmarkService {
    void toggleBookmark(String userEmail, Des desCode, String bmUrl);
}



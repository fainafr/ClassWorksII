//package com.library.service;
//
//import java.time.LocalDateTime;
//
//import javax.transaction.Transactional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import com.library.entity.Author;
//import com.library.entity.AuthorId;
//import com.library.repo.IAuthorRepo;
//
///**
// * Tests that a database does not gets re-created with blank values with
// * createDatabaseIfNotExist=true
// * 
// * @author shtirlitz
// *
// */
//@Component
//public class LibraryChecker implements CommandLineRunner {
//
//	private static final AuthorId JOYCE = new AuthorId("James", "Joyce");
//
//	@Autowired
//	IAuthorRepo authorRepo;
//
//	@Transactional
//	@Override
//	public void run(String... args) throws Exception {
//
//		Author persistedAuthor = authorRepo.findById(JOYCE).orElse(new Author(JOYCE));
//
//		persistedAuthor.getId().setFirstName(persistedAuthor.getId().getFirstName() + LocalDateTime.now().getSecond());
//
//		System.out.println("With each launch of application, a new author must be added, until there are 61 authors"
//				+ authorRepo.findAll().toString());
//
//		System.out
//				.println("61 because one plus distinct 60 authors for each unique second in a minute, now there is/are"
//						+ authorRepo.count() + " author/s");
//
//		authorRepo.save(persistedAuthor);
//
//	}
//}

package com.xiwen.yxw.build;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;


public class CreateIndex {
	
	private String indexHome;
	
	public void setIndexHome(String indexHome) {
		this.indexHome = indexHome;
	}
	
	public String getIndexHome() {
		return this.indexHome;
	}
	
	public void createIndex() throws IOException {
		Analyzer analyzer = new SimpleAnalyzer();
		IndexWriterConfig conf = new IndexWriterConfig(analyzer);
		conf.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
		
		Directory dir = new MMapDirectory(Paths.get(getIndexHome()));
		IndexWriter writer = new IndexWriter(dir, conf);
		
		Document doc = new Document();
		FieldType fieldType = new FieldType();
		fieldType.setStored(true);
		fieldType.setIndexOptions(IndexOptions.DOCS);
		fieldType.setStoreTermVectors(true);
		doc.add(new Field("title", "lucene introduction xiwen yxw wang ying  wang qian deng deng", fieldType));
		doc.add(new Field("content", "lucene works well xi wen", fieldType));
		writer.addDocument(doc);
		writer.close();
	}
	
}

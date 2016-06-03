package com.xiwen.yxw.document;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;


public class Test {
	
	private static final String INDEX_HOME = "./search_data";
	
	public static void main(String[] args) {
		Analyzer analyzer = new SimpleAnalyzer();
		IndexWriterConfig conf = new IndexWriterConfig(analyzer);
		conf.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
		
		try {
			Directory dir = new MMapDirectory(Paths.get(INDEX_HOME));
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
			
			IndexReader reader=DirectoryReader.open(dir);
			IndexSearcher searcher = new IndexSearcher(reader);
			Term term=new Term("content", "xi");
			
			TermQuery query=new TermQuery(term);
			TopDocs topdocs=searcher.search(query, 5);
			ScoreDoc[] scoreDocs=topdocs.scoreDocs; 
			for (ScoreDoc item : scoreDocs) {
				int docID = item.doc;
				Document document = searcher.doc(docID); 
				System.out.println("content = "+document.get("content") + "|||title = "+document.get("title"));  
	            System.out.println("id--" + item.doc + "---scors--" + item.score+"---index--"+item.shardIndex);  
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
}

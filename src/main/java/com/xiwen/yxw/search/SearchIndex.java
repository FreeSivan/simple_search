package com.xiwen.yxw.search;

import java.io.IOException;
import java.nio.file.Paths;

import com.xiwen.yxw.domain.RequestBean;
import com.xiwen.yxw.domain.ResponseBean;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;

public class SearchIndex {
	
	private String indexHome;
	
	public ResponseBean searchIndex(RequestBean requestBean) throws IOException {
        ResponseBean responseBean = new ResponseBean();
		Directory dir = new MMapDirectory(Paths.get(getIndexHome()));
		IndexReader reader=DirectoryReader.open(dir);
		IndexSearcher searcher = new IndexSearcher(reader);
		Term term=new Term(requestBean.getSearchField(), requestBean.getSearchKey());
		
		TermQuery query=new TermQuery(term);
		TopDocs topdocs=searcher.search(query, 5);
		ScoreDoc[] scoreDocs=topdocs.scoreDocs; 
		for (ScoreDoc item : scoreDocs) {
			int docID = item.doc;
			Document document = searcher.doc(docID);
            responseBean.setSearchResult("content = "+document.get("content") + "|||title = "+document.get("title\n") +
                    "id--" + item.doc + "---scors--" + item.score+"---index--"+item.shardIndex);
		}
        return responseBean;
	}

	public String getIndexHome() {
		return indexHome;
	}

	public void setIndexHome(String indexHome) {
		this.indexHome = indexHome;
	}
}

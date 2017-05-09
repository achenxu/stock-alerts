package ar.com.sac.services;

import ar.com.sac.model.IStockWrapper;
import ar.com.sac.model.Quote;
import ar.com.sac.model.SimulatorStockWrapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * This is a "STUB" Service, useful for replacing real stockService in simulations controlling the quotes
 */
public class StockSimulatorService implements IStockService {
   
   private List<Quote> quotes = new ArrayList<>();

   public void setSimulationQuotes (List<Quote> quotes){
      this.quotes  = quotes;
   }

   @Override
   public IStockWrapper getStock( String symbol ) throws IOException {
      IStockWrapper stock = new SimulatorStockWrapper( symbol, quotes.get( 0 ) );
      return stock;
   }

   /**
    * Don't use this method in simulations
    */
   @Override
   public Map<String, IStockWrapper> getStocks( String[] symbol ) throws IOException {
      Map<String,IStockWrapper> resultMap = new HashMap<>();
      if(quotes.size() > 0){
         resultMap.put( symbol[0], new SimulatorStockWrapper( symbol[0], quotes.get( 0 ) ) );
      }
      
      return resultMap;
   }

   @Override
   public List<Quote> getHistory( String symbol ) throws IOException {
      return quotes;
   }

   @Override
   public List<Quote> getHistory( String symbol, Calendar from, Calendar to ) throws IOException {
      return quotes;
   }

   /**
    * Don't use this method in simulations
    */
   @Override
   public Map<String, List<Quote>> getHistory( String[] symbols, Calendar from, Calendar to ) throws IOException {
      // TODO implement this method correctly
      return new HashMap<String, List<Quote>>();
   }

   @Override
   public void importQuotes( Collection<Quote> quotes ) {
      this.quotes.addAll( quotes );
   }
   
   public List<String> getSymbols(){
      List<String> resultList = new ArrayList<>();
      if(quotes.size() > 0){
         resultList.add( quotes.get(0).getSymbol() );
      }
      return resultList;
   }

   /**
    * Don't use this method in simulations
    */
   @Override
   public void updateDBJob() {
   }

   @Override
   public void deleteStock( String symbol ) {
      quotes.clear();
   }

   /**
    * Don't use this method in simulations
    */
   @Override
   public void autoUpdateDBHistory( Integer fromYear ) {
   }

}

/**
    *
    */
   private static final class hFiles
   {
       // Wrapper function
       public static boolean Create( String Path )
       {
           File f = new File( Path );

           return Create( f );
       }

       /**
        *
        * @return
        */
       public static boolean Create( File filePath )
       {

           if( Exist( filePath ) == false )
           {
               try
               {
                   return filePath.createNewFile();
               }
               catch( Exception ex )
               {

               }

           }
           else
           {

           }

           return false;
       }

       // Wrapper Function
       public static boolean Remove( String path )
       {
           File f = new File( path );

           return Remove( f );
       }

       /**
        *
        * @return
        */
       public static boolean Remove( File filePath )
       {
           try
           {
               if( Exist( filePath ) )
                   filePath.delete();
           }
           catch( Exception Ex )
           {

           }

          return false;
       }

       // Wrapper Function
       public static boolean Exist( String path )
       {
           File f = new File( path );

           return Exist( f );
       }

       /**
        *
        * @return
        */
       public static boolean Exist( File filePath )
       {
           try
           {
               if( filePath.isFile() )
               {
                   return filePath.exists();
               }
           }
           catch( Exception ex )
           {

           }

           return false;
       }

       public static final class List
       {
           public static ArrayList<File> FilesInDirectory( File filePath )
           {
               ArrayList<File> ListOfFoundFiles = new ArrayList();

               File[] ListOfStuffFound = filePath.listFiles();

               if( ListOfStuffFound.length == 0 )
                   return null;

               for( File f : ListOfStuffFound )
               {

                   if( f.isFile() )
                   {
                       ListOfFoundFiles.add( f );
                   }

               }

               return ListOfFoundFiles;
           }
       }


   } // End hFiles

   /**
    *
    */
   private static final class hDirectories
   {
       // Wrapper Function
       public static boolean Create( String path, boolean createParents )
       {
           File f = new File( path );

           return Create( f,
                          createParents );
       }

       public static boolean Create( File Path, boolean createParents )
       {
           try
           {
               if( createParents == true )
               {
                   Path.mkdirs();
               }
               else
               {
                   Path.mkdir();
               }
           }
           catch( Exception Ex )
           {

           }

           return false;
       }

       // Wrapper function
       public static boolean Remove( String path )
       {
           File f = new File( path );

           return Remove( f );
       }

       public static boolean Remove( File Path )
       {
           try
           {
               if( Exist( Path ) == true )
               {
                   Path.delete();

                   return true;
               }
               else
               {
                   return false;
               }

           }
           catch( Exception ex )
           {

           }

           return false;
       }

       // Wrapper function
       public static boolean Exist( String path )
       {
           File f = new File( path );

           return Exist( f );
       }

       
   } // End Class hDirectories

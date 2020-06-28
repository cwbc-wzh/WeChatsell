package com.atwzh.sell.utils;

import java.io.*;

/**
 * 文件操作帮助类
 * @作者 lWX216425
 * @version [版本号, 2014-6-19]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class FileUtil
{


    /**
     * [读取txt文件的内容]
     * @param [参数1] [参数1说明]
     * @param [参数2] [参数2说明]
     * @return [返回类型说明]
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String readTxtFile(String filePath, String encode)
            throws Exception
    {

        String result = "";
        InputStream in = null;
        InputStreamReader reader = null;
        BufferedReader bufferedReader = null;
        try
        {
            in = new FileInputStream(filePath);
            if (!"".equals(encode))
            {
                reader = new InputStreamReader(in, encode);
            }
            else
            {
                reader = new InputStreamReader(in);
            }

            bufferedReader = new BufferedReader(reader);

            StringBuffer sb = new StringBuffer();
            String line = bufferedReader.readLine();
            while (line != null)
            {
                sb.append(line);
                sb.append("\r\n");
                line = bufferedReader.readLine();
            }
            result = sb.toString();
        }
        catch (FileNotFoundException e)
        {
            throw e;
        }
        catch (IOException e)
        {
            throw e;
        }
        finally
        {
            try
            {
                if (bufferedReader != null)
                {
                    bufferedReader.close();
                }
                else if (reader != null)
                {
                    reader.close();
                }
                else if (in != null)
                {
                    in.close();
                }
            }

            catch (IOException e)
            {
                throw e;
            }

        }

        return result;
    }

    /**
     * 
     * [保存数据到指定的文件目录]
     * @param [参数1] [参数1说明]
     * @param [参数2] [参数2说明]
     * @return [返回类型说明]
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static boolean saveFile(String file_dirPath, String zipUrl,
                                   byte[] data)
    {


        boolean createSuccess = true;

        File file_dir = new File(file_dirPath);
        if (!file_dir.exists())
        {
            boolean mkResult = file_dir.mkdirs();
        }

        try
        {
            File zipFile = new File(zipUrl);
            OutputStream out = new FileOutputStream(zipFile);
            out.write(data);
            out.flush();
            out.close();
        }
        catch (IOException e)
        {
            createSuccess = false;
        }
        return createSuccess;

    }
    
   
    /**
     * 复制单个文件
     * 
     * @param srcFileName 待复制的文件名
     * @param descFileName 目标文件名
     * @param overlay 如果目标文件存在，是否覆盖
     * @return 如果复制成功返回true，否则返回false
     */
    public static boolean copyFile(String srcFileName, String destFileName,
                                   boolean overlay)
    {
        File srcFile = new File(srcFileName);

        // 判断源文件是否存在
        if (!srcFile.exists())
        {
            return false;
        }
        else if (!srcFile.isFile())
        {
            return false;
        }

        // 判断目标文件是否存在
        File destFile = new File(destFileName);
        
        if (destFile.exists())
        {
            // 如果目标文件存在并允许覆盖
            if (overlay)
            {
                // 删除已经存在的目标文件，无论目标文件是目录还是单个文件
                new File(destFileName).delete();
            }
        }
        else
        {
            // 如果目标文件所在目录不存在，则创建目录
            if (!destFile.getParentFile().exists())
            {
                // 目标文件所在目录不存在
                if (!destFile.getParentFile().mkdirs())
                {
                    // 复制文件失败：创建目标文件所在目录失败
                    return false;
                }
            }
        }

        // 复制文件
        int byteread = 0; // 读取的字节数
        
        InputStream in = null;
        OutputStream out = null;

        try
        {
            in = new FileInputStream(srcFile);
            out = new FileOutputStream(destFile);
            byte[] buffer = new byte[1024];

            while ((byteread = in.read(buffer)) != -1)
            {
                out.write(buffer, 0, byteread);
            }
            return true;
        }
        catch (FileNotFoundException e)
        {
            return false;
        }
        catch (IOException e)
        {
            return false;
        }
        finally
        {
            try
            {
                if (out != null)
                    out.flush();
                    out.close();
                if (in != null)
                    in.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

}

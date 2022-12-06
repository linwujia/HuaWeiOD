# HuaWeiOD
华为OD机考三道题的代码，以及考试过程中的一个记录

如果需要AndroidStudio，运行main方法，首先需要修改AndroidStudio自动生成的grade.xml文件  
在.idea目录下，有个gradle.xml，打开
```
<?xml version="1.0" encoding="UTF-8"?>
<project version="4">
  <component name="GradleMigrationSettings" migrationVersion="1" />
  <component name="GradleSettings">
    <option name="linkedExternalProjectsSettings">
      <GradleProjectSettings>
        <option name="delegatedBuild" value="false" /><!--增加这一行-->
        <option name="testRunner" value="GRADLE" />
        <option name="distributionType" value="DEFAULT_WRAPPED" />
        <option name="externalProjectPath" value="$PROJECT_DIR$" />
        <option name="modules">
          <set>
            <option value="$PROJECT_DIR$" />
            <option value="$PROJECT_DIR$/app" />
            <option value="$PROJECT_DIR$/lib" />
          </set>
        </option>
      </GradleProjectSettings>
    </option>
  </component>
</project>
```
在GradleProjectSettings节点下增加
`<option name="delegatedBuild" value="false" />`

具体的文档查看[华为OD 机考记录](https://github.com/linwujia/HuaWeiOD/blob/master/%E5%8D%8E%E4%B8%BAOD%E6%9C%BA%E8%80%83/%E5%8D%8E%E4%B8%BAOD%20%E6%9C%BA%E8%80%83%E8%AE%B0%E5%BD%95.md)

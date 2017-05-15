 
   
    一、MVP介绍
     随着UI创建技术的功能日益增强，UI层也履行着越来越多的职责。为了更好地细分视图(View)与模型(Model)的功能，让View专注于处理数据的可视化以及与用户的交互，同时让Model只关系数据的处理，基于MVC概念的MVP(Model-View-Presenter)模式应运而生。
         在MVP模式里通常包含4个要素：
         (1)View:负责绘制UI元素、与用户进行交互(在Android中体现为Activity);
         (2)View interface:需要View实现的接口，View通过View interface与Presenter进行交互，降低耦合，方便进行单元测试;
         (3)Model:负责存储、检索、操纵数据(有时也实现一个Model interface用来降低耦合);
         (4)Presenter:作为View与Model交互的中间纽带，处理与用户交互的负责逻辑。
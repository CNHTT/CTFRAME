 
   
    一、MVP介绍
  <h6>随着UI创建技术的功能日益增强，UI层也履行着越来越多的职责。为了更好地细分视图(View)与模型(Model)的功能，让View专注于处理数据的可视化以及与用户的交互，
     同时让Model只关系数据的处理，基于MVC概念的MVP(Model-View-Presenter)模式应运而生。</h6>
  <font size="6" color="red">在MVP模式里通常包含4个要素：</font>
  <ol>
    <li>View:负责绘制UI元素、与用户进行交互(在Android中体现为Activity);</li>
    <li>View interface:需要View实现的接口，View通过View interface与Presenter进行交互，降低耦合，方便进行单元测试;</li>
    <li>Model:负责存储、检索、操纵数据(有时也实现一个Model interface用来降低耦合);</li>
    <li>Presenter:作为View与Model交互的中间纽带，处理与用户交互的负责逻辑。</li>
  </ol>
  
     二、为什么使用MVP模式    
<h6><font size="5"> 在Android开发中，Activity并不是一个标准的MVC模式中的Controller，它的首要职责是加载应用的布局和初始化用户界面，并接受并处理来自用户的操作请求，进而作出响应。随着界面及其逻辑的复杂度不断提升，Activity类的职责不断增加，以致变得庞大臃肿。当我们将其中复杂的逻辑处理移至另外的一个类（Presneter）中时，Activity其实就是MVP模式中View，它负责UI元素的初始化，建立UI元素与Presenter的关联（Listener之类），同时自己也会处理一些简单的逻辑（复杂的逻辑交由Presenter处理）.
<br>&emsp;&emsp;另外，回想一下你在开发Android应用时是如何对代码逻辑进行单元测试的？是否每次都要将应用部署到Android模拟器或真机上，然后通过模拟用户操作进行测试？然而由于Android平台的特性，每次部署都耗费了大量的时间，这直接导致开发效率的降低。而在MVP模式中，处理复杂逻辑的Presenter是通过interface与View(Activity)进行交互的，这说明了什么？说明我们可以通过自定义类实现这个interface来模拟Activity的行为对Presenter进行单元测试，省去了大量的部署及测试的时间。</h5></font>
  
  
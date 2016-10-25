using System;
using clojure.lang;
using System.Threading;
using clojure.clr.api;

namespace firstclojure
{
	class MainClass
	{
		public static void Main (string[] args)
		{
			/*IFn load = Clojure.var("clojure.core", "load-string");
			Console.WriteLine(Convert.ToString(load.invoke("(reduce * (range 2 10))")));
			Console.WriteLine ("Hello World!");
			*/

			hello.net fromclojure = new hello.net();
			string sunset = fromclojure.getsunset("Tokyo");
			Console.WriteLine("Sunset today in Tokyo is {0}", sunset);
			Thread.Sleep (2000);
		}
	}
}

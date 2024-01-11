import {
    Tooltip,
    TooltipContent,
    TooltipProvider,
    TooltipTrigger,
} from "@/components/ui/tooltip";

interface HintProps {
    children: React.ReactNode;
    label: string;
    asChild?: boolean;
    side?: "left" | "right" | "top" | "bottom";
    align?: "start" | "center" | "end";
}

export const Hint = ({
    children,
    label,
    asChild = false,
    side = "bottom",
    align = "center",
}: HintProps) => {
    return (
        <TooltipProvider>
            <Tooltip delayDuration={0}>
                <TooltipTrigger asChild={asChild}>{children}</TooltipTrigger>
                <TooltipContent
                    className="text-black bg-white"
                    side={side}
                    align={align}
                >
                    <p className="font-semibold">{label}</p>
                </TooltipContent>
            </Tooltip>
        </TooltipProvider>
    );
};
